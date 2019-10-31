from graphviz import Digraph
import json
import os


cwd = os.getcwd()
files_path = os.path.join(cwd,"..")
files_path = os.path.join(files_path,"..")
onlyfiles = [f for f in os.listdir(files_path) if os.path.isfile(os.path.join(files_path, f))]

for output_file in onlyfiles:
    f = open(os.path.join(files_path, output_file),"r")
    contents = f.read()
    components = contents.split("\n")
    
    vertices = []
    edges = []
    for component in components:
        if( component[:6] == "vertex"):
            vertices.append(component[7:])
        elif( component[:4] == "edge"):
            edges.append(component[5:])

    dot = Digraph(comment="PDG", format='png')

    for vertex in vertices:
        vertex_dict = json.loads(vertex)
        dot.node( str(vertex_dict["id"]), str(vertex_dict["label"]) )

    for edge in edges:
        edge_dict = json.loads(edge)
        if( str(edge_dict["label"]) == ""):
            dot.edge( str(edge_dict["src"]), str(edge_dict["targ"]) ,label=str(edge_dict["label"]), style = 'dashed')
        else:
            dot.edge( str(edge_dict["src"]), str(edge_dict["targ"]) ,label=str(edge_dict["label"]))

    print(dot)
    dot.render('../'+output_file+'.gv', view=True)
