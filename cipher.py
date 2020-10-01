def xor_strings(xs, ys):
    return "".join(chr(ord(x) ^ ord(y)) for x, y in zip(xs, ys))

a="0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104"
b="6d79584f526b65796d79584f526b65796d79584f526b65796d79584f526b65796d79584f526b65796d79584f526b6579"
binary_a = a.decode("hex")
binary_b = b.decode("hex")
xored = xor_strings(binary_a, binary_b)
print(xored)
