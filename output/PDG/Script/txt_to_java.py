import os

cwd = os.getcwd()

dataset = os.path.join(cwd,'..')
dataset = os.path.join(dataset,'..')
dataset = os.path.join(dataset,'..')
dataset = os.path.join(dataset,'ProgramData')
folders = os.listdir(dataset)


for folder in folders:
    folder = os.path.join(dataset,folder)
    files = os.listdir(folder)
    for f in files:
        f = os.path.join(folder,f)
        base = os.path.splitext(f)[0]
        os.rename(f, base + ".java")