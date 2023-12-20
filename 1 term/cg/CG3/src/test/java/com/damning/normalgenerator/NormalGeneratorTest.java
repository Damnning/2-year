package test.java.com.damning.normalgenerator;

import cg.vsu.render.math.vector.Vector3f;
import main.java.com.damning.model.Model;
import main.java.com.damning.model.Polygon;
import main.java.com.damning.normalgenerator.NormalGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class NormalGeneratorTest {
    private NormalGenerator normalGenerator;
    private Model model;

    @BeforeEach
    void setUp() {
        normalGenerator = new NormalGenerator();
        model = new Model();
    }

    @Test
    void generateNormalsTest1() {
        addVertices(model);
        normalGenerator.generateNormals(model);
        Assertions.assertTrue(model.normals.isEmpty());
    }

    @Test
    void generateNormalsTest2() {
        addPolygon(model);
        normalGenerator.generateNormals(model);
        List<Vector3f> expectedNormals = List.of(
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1)
                );
        Assertions.assertEquals(expectedNormals, model.normals);
    }

    @Test
    void generateNormalsTest3() {
        addCube(model);
        normalGenerator.generateNormals(model);
        List<Vector3f> expectedNormals = List.of(
                Vector3f.zero().add(0, 0, -1).add(0, -1, 0).add(-1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, -1).add(0, -1, 0).add(1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, -1).add(0, 1, 0).add(-1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, -1).add(0, 1, 0).add(1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, 1).add(0, -1, 0).add(-1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, 1).add(0, -1, 0).add(1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, 1).add(0, 1, 0).add(-1, 0, 0).nor(),
                Vector3f.zero().add(0, 0, 1).add(0, 1, 0).add(1, 0, 0).nor()
        );
        Assertions.assertArrayEquals(expectedNormals.toArray(), model.normals.toArray());

    }

    @Test
    void generateNormalsTest4() {
        addPyramid(model);
        normalGenerator.generateNormals(model);
        List<Vector3f> expectedNormals = List.of(
                getVertexNormal(
                        List.of(new Vector3f(0, 0, -1), new Vector3f(0, -1, 0), new Vector3f(-1, 0, 0)
                        )),
                getVertexNormal(
                        List.of(new Vector3f(0, 0, -1), new Vector3f(0, -1, 0), new Vector3f(1, 1, 1)
                        )),
                getVertexNormal(
                        List.of(new Vector3f(0, 0, -1), new Vector3f(1, 1, 1), new Vector3f(-1, 0, 0)
                        )),
                getVertexNormal(
                        List.of(new Vector3f(1, 1, 1), new Vector3f(0, -1, 0), new Vector3f(-1, 0, 0)
                        ))
        );
        Assertions.assertArrayEquals(expectedNormals.toArray(), model.normals.toArray());
    }

    private Vector3f getVertexNormal(List<Vector3f> polygonsNormals) {
        Vector3f normal = Vector3f.zero();
        for (Vector3f polygonNormal : polygonsNormals) {
            normal.add(polygonNormal.nor());
        }
        return normal.nor();
    }

    private void addCube(Model model) {
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(1, 0, 1));
        model.vertices.add(new Vector3f(0, 1, 1));
        model.vertices.add(new Vector3f(1, 1, 1));
        model.polygons.add(getPolygon(List.of(0, 2, 3, 1)));
        model.polygons.add(getPolygon(List.of(4, 5, 7, 6)));
        model.polygons.add(getPolygon(List.of(0, 1, 5, 4)));
        model.polygons.add(getPolygon(List.of(1, 3, 7, 5)));
        model.polygons.add(getPolygon(List.of(3, 2, 6, 7)));
        model.polygons.add(getPolygon(List.of(2, 0, 4, 6)));
    }

    private void addPyramid(Model model) {
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.polygons.add(getPolygon(List.of(0, 2, 1)));
        model.polygons.add(getPolygon(List.of(0, 3, 2)));
        model.polygons.add(getPolygon(List.of(0, 1, 3)));
        model.polygons.add(getPolygon(List.of(1, 2, 3)));
    }

    private void addVertices(Model model) {
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
    }

    private void addPolygon(Model model) {
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.polygons.add(getPolygon(List.of(0, 1, 2, 3)));
    }

    private Polygon getPolygon(List<Integer> vertexIndices) {
        Polygon polygon = new Polygon();
        ArrayList<Integer> vertexIndicesCopy = new ArrayList<>(vertexIndices);
        polygon.setVertexIndices(vertexIndicesCopy);
        return polygon;
    }
}
