from graphviz import Digraph
import json


f = open("output","r")
contents = f.read()
components = contents.split("\n")
vertices = []
edges = []
for component in components:
    if( component[:6] == "vertex"):
        vertices.append(component[7:])
    elif( component[:4] == "edge"):
        edges.append(component[5:])

dot = Digraph(comment="PDG")

for vertex in vertices:
    vertex_dict = json.loads(vertex)
    dot.node( str(vertex_dict["id"]), str(vertex_dict["label"]) )

for edge in edges:
    edge_dict = json.loads(edge)
    dot.edge( str(edge_dict["src"]), str(edge_dict["targ"]) ,label=str(edge_dict["label"]))

print(dot)
dot.render('PDG.gv', view=True)



# vertex = components[12]

# vertex = vertex[5:]
# print(vertex)
# # dic = json.loads(vertex)
# # print(dic["id"])