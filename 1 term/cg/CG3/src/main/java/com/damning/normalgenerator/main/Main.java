package main.java.com.damning.normalgenerator.main;

import cg.vsu.render.math.vector.Vector3f;
import main.java.com.damning.model.Model;
import main.java.com.damning.model.Polygon;
import main.java.com.damning.normalgenerator.NormalGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        model.polygons.add(polygon);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(model);
        model.normals.forEach(System.out::println);
    }
}