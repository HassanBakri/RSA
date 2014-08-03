RSA
===

Simple java impelementation for public-key alogrithm
there is known bugs in decripting binary files
 and this buge cased by the nature of toByteAray() method in BigInteger Class
 which is yeldes the number in the 2'complements form 
 and there is additional 0 in positive numbers 
