package graph;

import java.util.*;

import com.google.gson.Gson;

public class Edge {

    public int src;
    public int targ;
    public String label;
    Graph g;

    public Edge(int src, int targ, String label, Graph g) {
        this.src = src;
        this.targ = targ;
        this.label = label;
        this.g = g;
    }

    public String Serialize() {
        Map<String,Object> M = new LinkedHashMap<String,Object>();
        M.put("src", src);
        M.put("targ", targ);
        M.put("label", label);
        M.put("src_label", g.label(src));
        M.put("targ_label", g.label(targ));
        return "edge\t" + (new Gson()).toJson(M);
    }
}
