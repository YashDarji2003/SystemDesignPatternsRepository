class LSP
{
public static void main(String[] abc)
{
B b  = new B();
b.m1(b);
}
}

class A
{
 public void m1(A a)
{
System.out.println("M1 From A");
}
 public void m2()
{
System.out.println("M2 From A");
}
}
class B extends A
{
public void m3()
{
System.out.println("M3 From B");
}
}