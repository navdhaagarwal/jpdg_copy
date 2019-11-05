import os
import re


def replace_right(source, target, replacement, replacements=None):
    return replacement.join(source.rsplit(target, replacements))

cwd = os.getcwd()
files_path = os.path.join(cwd,"ProgramData")
output_path = os.path.join(cwd,"UpdatedData")
files = os.listdir(files_path)
os.chdir(files_path)
count = 0


for data_file in files:
    print(data_file)
    f = open(data_file, "r")
    contents = f.read().split("\n")

    data_file_name = "Test_"+str(count)
    package = 0
    index = 0
    for line in contents:
        if("package" in line):
            line = "package UpdatedData;"
            package = 1
        if("public class" in line):
            words = re.split(' |{', line)
            class_name = words[2]
            k = line.find("public class")
            line = line[:k+12] + line[k+12:].replace(class_name,data_file_name, 1)
            # print(line)

        contents[index] = line
        index += 1
    
    if(package == 0):
        contents.insert(0, "package UpdatedData;")
    
    count += 1

    f.close()
    path = os.path.join(output_path,data_file_name+'.java')
    f = open(str(path), "w+")
    for line in contents:
        f.write(line + '\n')
    f.close()
