ldc 6
ldc 4
st 0
st 1
ld 0
ld 1
: main
cmp
br f1
ld 0
br end
: f1
ldc 1 
cmp
br f2
ld 0
ld 1
sub
st 1
ld 0
ld 1
jmp main
: f2
ld 1
ld 0
sub
st 0
ld 0
ld 1
jmp main
: end
ret