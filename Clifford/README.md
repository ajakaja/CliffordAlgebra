## Clifford / geometric algebra helper.

Console program for generating multiplication tables for [geometric algebras](http://en.wikipedia.org/wiki/Geometric_algebra).

It's basic and not extensively tested for now. It got the right results for the ones I needed to know about.
You can rename and reorder elements to arrange it as you like, but there's no support yet for rewriting elements as their dual
(for example, labeling something as i*o3 ) without doing it manually.

Type ? for commands in the program.



Demo: 

	> new 1 -1 -1 -1
	Made new algebra algebra0.
	> show
	Algebra algebra0:
	0-vector 1
	1-vector v1: v1
	1-vector v2: v2
	1-vector v3: v3
	1-vector v4: v4
	2-vector o1: v1 ^ v2
	2-vector o2: v3 ^ v1
	2-vector o3: v2 ^ v3
	2-vector o4: v1 ^ v4
	2-vector o5: v4 ^ v2
	2-vector o6: v3 ^ v4
	3-vector g1: v1 ^ v2 ^ v3
	3-vector g2: v2 ^ v1 ^ v4
	3-vector g3: v3 ^ v1 ^ v4
	3-vector g4: v2 ^ v3 ^ v4
	4-vector i: v1 ^ v2 ^ v3 ^ v4
	Multiplication Table: 
	       1      v1      v2      v3      v4      o1      o2      o3      o4      o5      o6      g1      g2      g3      g4       i
	      v1       1      o1     -o2      o4      v2     -v3      g1      v4      g2     -g3      o3      o5     -o6       i      g4
	      v2     -o1      -1      o3     -o5      v1      g1     -v3      g2      v4      g4     -o2     -o4       i     -o6     -g3
	      v3      o2     -o3      -1      o6      g1     -v1      v2      g3      g4     -v4     -o1      -i     -o4     -o5      g2
	      v4     -o4      o5     -o6      -1     -g2      g3      g4      v1     -v2      v3      -i      o1     -o2     -o3      g1
	      o1     -v2     -v1      g1     -g2       1      o3      o2      o5      o4       i      v3     -v4      g4      g3      o6
	      o2      v3      g1      v1      g3     -o3       1     -o1      o6      -i      o4      v2      g4      v4      g2     -o5
	      o3      g1      v3     -v2      g4     -o2      o1      -1       i     -o6      o5     -v1      g3     -g2     -v4     -o4
	      o4     -v4      g2      g3     -v1     -o5     -o6       i       1     -o1     -o2     -g4      v2      v3     -g1      o3
	      o5      g2     -v4      g4      v2     -o4      -i      o6      o1      -1     -o3     -g3     -v1      g1     -v3      o2
	      o6     -g3      g4      v4     -v3       i     -o4     -o5      o2      o3      -1     -g2      g1      v1     -v2     -o1
	      g1      o3     -o2     -o1       i      v3      v2     -v1      g4      g3      g2      -1     -o6     -o5     -o4     -v4
	      g2      o5     -o4       i      o1     -v4     -g4     -g3      v2     -v1     -g1      o6      -1      o3      o2     -v3
	      g3     -o6      -i     -o4     -o2     -g4      v4      g2      v3     -g1      v1      o5     -o3      -1      o1      v2
	      g4      -i     -o6     -o5     -o3     -g3     -g2     -v4      g1     -v3     -v2      o4     -o2     -o1       1     -v1
	       i     -g4      g3     -g2     -g1      o6     -o5     -o4      o3      o2     -o1      v4      v3     -v2      v1      -1
