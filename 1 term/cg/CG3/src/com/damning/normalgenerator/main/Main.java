package com.damning.normalgenerator.main;

import cg.vsu.render.math.vector.Vector3f;
import com.damning.model.Model;
import com.damning.model.Polygon;
import com.damning.normalgenerator.NormalGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
/*        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        model.vertices.add(new Vector3f(1, 0, 1));
        model.vertices.add(new Vector3f(0, 1, 1));
        model.vertices.add(new Vector3f(1, 1, 1));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(0, 2, 3, 1)));
        model.polygons.add(polygon1);
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(4, 5, 7, 6)));
        model.polygons.add(polygon2);
        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 5, 4)));
        model.polygons.add(polygon3);
        Polygon polygon4 = new Polygon();
        polygon4.setVertexIndices(new ArrayList<>(Arrays.asList(1, 3, 7, 5)));
        model.polygons.add(polygon4);
        Polygon polygon5 = new Polygon();
        polygon5.setVertexIndices(new ArrayList<>(Arrays.asList(3, 2, 6, 7)));
        model.polygons.add(polygon5);
        Polygon polygon6 = new Polygon();
        polygon6.setVertexIndices(new ArrayList<>(Arrays.asList(2, 0, 4, 6)));
        model.polygons.add(polygon6);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(model);
        model.normals.forEach((i) -> System.out.println(i.x+", "+ i.y + ", " + i.z));
        System.out.println(model.vertices);*/
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0, 0, 1));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(0, 2, 1)));
        model.polygons.add(polygon1);
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(0, 3, 2)));
        model.polygons.add(polygon2);
        Polygon polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        model.polygons.add(polygon3);
        Polygon polygon4 = new Polygon();
        polygon4.setVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 3)));
        model.polygons.add(polygon4);
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(model);
        model.normals.forEach((i) -> System.out.println(i.x + ", " + i.y + ", " + i.z));
    }
}