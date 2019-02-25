package Task3.kotlin;

public class Pow {
  public int pow(int a, int b) {
    int res = 1;
    for (int i = 1; i < b; i++) {
      res *= a;
    }

    return res;
  }
}
