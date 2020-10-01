with open("3.txt") as f:

    content = f.readlines()

content = [x.strip() for x in content]

out = ""

res1 = [content[i] for i in range(len(content)) if i % 2 != 0]

res2 = [content[i] for i in range(len(content)) if i % 2 == 0] 

print(len(res1),len(res2))

for i,l in enumerate(res2):

    print(i,res2[i],res1[i])

    for j,m in enumerate(res2[2]):

        if not(res2[i][j] == res1[i][j]):

            out = out+res1[i][j]

print(out)

