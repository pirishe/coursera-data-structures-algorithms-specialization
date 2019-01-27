import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main6
{
    public static void main(String[] args)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            byte n = Byte.valueOf(br.readLine());
            String[] strArray = br.readLine().split(" ");
            ArrayList<Integer> a = new ArrayList<>();
            for (byte i = 0; i < n; i++) {
                a.add(Integer.valueOf(strArray[i]));
            }
            a.sort((Integer i1, Integer i2) -> {
                Integer num1 = Integer.valueOf(String.valueOf(i1) + String.valueOf(i2));
                Integer num2 = Integer.valueOf(String.valueOf(i2) + String.valueOf(i1));
                return num2.compareTo(num1);
            });
            a.forEach(str -> System.out.print(str + ""));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
