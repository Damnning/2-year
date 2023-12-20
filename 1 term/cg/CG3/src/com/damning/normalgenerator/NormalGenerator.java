package com.damning.normalgenerator;


import cg.vsu.render.math.vector.Vector3f;
import com.damning.model.Model;
import com.damning.model.Polygon;

import java.util.*;
import java.util.stream.Collectors;

public class NormalGenerator {
    /**
     * @param model - model to generate normals
     */
    public void generateNormals(Model model) {
        HashMap<Integer, List<Polygon>> verticesPolygonsMap = getVertexPolygonsMap(model.polygons);
        HashMap<Polygon, Vector3f> polygonsNormalsMap = getPolygonNormalMap(model.polygons, model.vertices);
        ArrayList<Vector3f> normals = new ArrayList<>();
        List<Vector3f> temp;
        for (int i = 0; i < model.vertices.size(); i++) {
            temp = verticesPolygonsMap.get(i).stream().map(polygonsNormalsMap::get).collect(Collectors.toList());
            normals.add(getVertexNormal(temp));
        }
        model.normals = normals;
        setPolygonsNormals(model.polygons);
    }

    /**
     * @param polygonsNormals - list of normals for polygons around vertex
     * @return normal for vertex
     */
    private Vector3f getVertexNormal(List<Vector3f> polygonsNormals) {
        Vector3f normal = Vector3f.zero();
        for (Vector3f polygonNormal : polygonsNormals) {
            normal.add(polygonNormal);
        }
        return normal.nor();
    }

    /**
     * @param polygons - list of polygons
     * @param vertices - list of vertices
     * @return map of polygon to normal
     */
    private HashMap<Polygon, Vector3f> getPolygonNormalMap(List<Polygon> polygons, List<Vector3f> vertices) {
        HashMap<Polygon, Vector3f> polygonsNormalMap = new HashMap<>();
        for (Polygon p : polygons) {
            polygonsNormalMap.put(p, getPolygonNormal(p, getPolygonVertices(p, vertices)));
        }
        return polygonsNormalMap;
    }

    /**
     * @param polygons - list of polygons
     * @return map of vertex indices to list of polygons that contain this vertex
     */
    private HashMap<Integer, List<Polygon>> getVertexPolygonsMap(List<Polygon> polygons) {
        HashMap<Integer, List<Polygon>> polygonsMap = new HashMap<>();
        for (Polygon p : polygons) {
            for (Integer i : p.getVertexIndices()) {
                if (!polygonsMap.containsKey(i)) {
                    polygonsMap.put(i, new ArrayList<>());
                }
                polygonsMap.get(i).add(p);
            }
        }
        return polygonsMap;
    }

    /**
     * Copies the indices of the vertices to the normal indices
     * @param polygons - list of polygons
     */
    private void setPolygonsNormals(List<Polygon> polygons){
        for (Polygon p : polygons) {
            p.setNormalIndices(p.getVertexIndices());
        }
    }
    /**
     * @param p - polygon
     * @param vertices - list of vertices
     * @return polygon normal
     */
    private Vector3f getPolygonNormal(Polygon p, List<Vector3f> vertices) {
        if(p.getVertexIndices().size() > 2) {
            Vector3f a = new Vector3f(vertices.get(1)).
                    sub(vertices.get(0));
            Vector3f b = new Vector3f(vertices.get(2)).
                    sub(vertices.get(0));
            return a.crs(b).nor();
        }
        return Vector3f.zero();
    }

    /**
     * @param p - polygon
     * @param vertices - list of vertices
     * @return list of polygon vertex coordinates
     */
    public List<Vector3f> getPolygonVertices(Polygon p, List<Vector3f> vertices) {
        return p.getVertexIndices().stream().map(vertices::get).collect(Collectors.toList());
    }
/*    public List<Vector3f> generateVerticesNormals(Model model) {
        List<Vector3f> normals = new ArrayList<>();
        return null;
    }


    public List<Vector3f> generatePolygonsNormals(List<Polygon> polygons, List<Vector3f> vertices) {
        List<Vector3f> normals = new ArrayList<>();
        for (Polygon p : polygons) {
            if (p.getVertexIndices().size() > 2) { // todo: check if direction is correct
                Vector3f a = new Vector3f(vertices.get(p.getVertexIndices().get(1))).
                        sub(vertices.get(p.getVertexIndices().get(0)));
                Vector3f b = new Vector3f(vertices.get(p.getVertexIndices().get(2))).
                        sub(vertices.get(p.getVertexIndices().get(0)));
                normals.add(a.crs(b).nor());
            }
        }
        return normals;
    }*/
}
