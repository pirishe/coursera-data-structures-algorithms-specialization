import java.util.Scanner;

public class Main5
{
  // table of Pisano periods
  // http://oeis.org/A001175/b001175.txt
  // from 2 to 1000 inclusive:
  private static int[] pisano = {
      3,
      8,
      6,
      20,
      24,
      16,
      12,
      24,
      60,
      10,
      24,
      28,
      48,
      40,
      24,
      36,
      24,
      18,
      60,
      16,
      30,
      48,
      24,
      100,
      84,
      72,
      48,
      14,
      120,
      30,
      48,
      40,
      36,
      80,
      24,
      76,
      18,
      56,
      60,
      40,
      48,
      88,
      30,
      120,
      48,
      32,
      24,
      112,
      300,
      72,
      84,
      108,
      72,
      20,
      48,
      72,
      42,
      58,
      120,
      60,
      30,
      48,
      96,
      140,
      120,
      136,
      36,
      48,
      240,
      70,
      24,
      148,
      228,
      200,
      18,
      80,
      168,
      78,
      120,
      216,
      120,
      168,
      48,
      180,
      264,
      56,
      60,
      44,
      120,
      112,
      48,
      120,
      96,
      180,
      48,
      196,
      336,
      120,
      300,
      50,
      72,
      208,
      84,
      80,
      108,
      72,
      72,
      108,
      60,
      152,
      48,
      76,
      72,
      240,
      42,
      168,
      174,
      144,
      120,
      110,
      60,
      40,
      30,
      500,
      48,
      256,
      192,
      88,
      420,
      130,
      120,
      144,
      408,
      360,
      36,
      276,
      48,
      46,
      240,
      32,
      210,
      140,
      24,
      140,
      444,
      112,
      228,
      148,
      600,
      50,
      36,
      72,
      240,
      60,
      168,
      316,
      78,
      216,
      240,
      48,
      216,
      328,
      120,
      40,
      168,
      336,
      48,
      364,
      180,
      72,
      264,
      348,
      168,
      400,
      120,
      232,
      132,
      178,
      120,
      90,
      336,
      120,
      48,
      380,
      120,
      180,
      96,
      144,
      180,
      190,
      96,
      388,
      588,
      280,
      336,
      396,
      120,
      22,
      300,
      136,
      150,
      112,
      72,
      40,
      624,
      48,
      168,
      90,
      240,
      42,
      108,
      280,
      72,
      440,
      72,
      240,
      108,
      296,
      60,
      252,
      456,
      448,
      48,
      600,
      228,
      456,
      72,
      114,
      240,
      80,
      84,
      52,
      168,
      160,
      174,
      312,
      144,
      238,
      120,
      240,
      330,
      648,
      60,
      560,
      120,
      252,
      60,
      168,
      1500,
      250,
      48,
      240,
      768,
      360,
      384,
      516,
      264,
      304,
      420,
      168,
      390,
      176,
      120,
      540,
      144,
      88,
      408,
      268,
      360,
      270,
      72,
      112,
      276,
      100,
      48,
      556,
      138,
      120,
      240,
      56,
      96,
      568,
      210,
      360,
      420,
      80,
      48,
      612,
      420,
      392,
      444,
      588,
      336,
      580,
      228,
      360,
      444,
      336,
      600,
      176,
      150,
      200,
      72,
      60,
      72,
      88,
      240,
      208,
      60,
      310,
      168,
      628,
      948,
      240,
      78,
      636,
      216,
      70,
      480,
      72,
      48,
      36,
      216,
      700,
      984,
      216,
      120,
      32,
      120,
      110,
      168,
      456,
      336,
      680,
      48,
      676,
      1092,
      152,
      180,
      30,
      72,
      784,
      264,
      240,
      348,
      232,
      168,
      174,
      1200,
      504,
      240,
      236,
      696,
      140,
      132,
      144,
      534,
      358,
      120,
      342,
      90,
      440,
      336,
      740,
      120,
      736,
      48,
      120,
      1140,
      432,
      120,
      748,
      180,
      1000,
      96,
      28,
      144,
      378,
      180,
      256,
      570,
      768,
      192,
      80,
      1164,
      264,
      588,
      388,
      840,
      144,
      336,
      520,
      396,
      780,
      120,
      796,
      66,
      144,
      600,
      200,
      408,
      420,
      150,
      1080,
      336,
      380,
      72,
      408,
      120,
      552,
      624,
      464,
      48,
      840,
      336,
      184,
      90,
      418,
      240,
      84,
      42,
      96,
      108,
      900,
      840,
      240,
      72,
      280,
      1320,
      430,
      72,
      868,
      240,
      280,
      108,
      144,
      888,
      438,
      60,
      336,
      252,
      888,
      456,
      220,
      1344,
      296,
      96,
      448,
      600,
      40,
      228,
      200,
      456,
      560,
      72,
      916,
      114,
      72,
      240,
      46,
      240,
      928,
      168,
      120,
      156,
      936,
      168,
      272,
      480,
      632,
      348,
      440,
      312,
      900,
      144,
      216,
      714,
      478,
      240,
      532,
      240,
      48,
      330,
      980,
      648,
      976,
      60,
      328,
      1680,
      490,
      120,
      252,
      252,
      120,
      120,
      560,
      168,
      498,
      1500,
      336,
      750,
      1008,
      48,
      100,
      240,
      728,
      768,
      254,
      360,
      592,
      768,
      72,
      516,
      1040,
      264,
      160,
      912,
      696,
      420,
      26,
      168,
      1048,
      390,
      400,
      528,
      180,
      120,
      1104,
      540,
      696,
      144,
      280,
      264,
      360,
      408,
      712,
      804,
      560,
      360,
      90,
      270,
      360,
      144,
      540,
      336,
      1096,
      276,
      120,
      300,
      126,
      48,
      624,
      1668,
      760,
      138,
      124,
      120,
      616,
      240,
      360,
      168,
      376,
      96,
      380,
      1704,
      432,
      420,
      568,
      360,
      570,
      420,
      760,
      240,
      1200,
      96,
      1156,
      612,
      776,
      420,
      336,
      1176,
      540,
      444,
      840,
      588,
      1176,
      336,
      90,
      1740,
      792,
      456,
      1188,
      360,
      720,
      444,
      88,
      336,
      598,
      600,
      600,
      528,
      408,
      150,
      220,
      600,
      1216,
      144,
      112,
      60,
      224,
      72,
      1228,
      264,
      40,
      240,
      1236,
      624,
      206,
      60,
      144,
      930,
      176,
      168,
      2500,
      1884,
      360,
      948,
      684,
      240,
      630,
      156,
      168,
      636,
      1280,
      216,
      112,
      210,
      840,
      960,
      640,
      72,
      1288,
      48,
      440,
      36,
      1296,
      216,
      290,
      2100,
      240,
      984,
      1308,
      216,
      260,
      120,
      888,
      96,
      658,
      120,
      220,
      330,
      504,
      168,
      720,
      456,
      336,
      336,
      448,
      2040,
      60,
      48,
      1348,
      2028,
      1800,
      1092,
      452,
      456,
      784,
      180,
      456,
      30,
      1368,
      72,
      1380,
      2352,
      456,
      264,
      756,
      240,
      138,
      348,
      240,
      696,
      460,
      168,
      360,
      174,
      104,
      1200,
      700,
      504,
      684,
      480,
      160,
      708,
      400,
      696,
      118,
      420,
      312,
      132,
      240,
      144,
      140,
      534,
      952,
      1074,
      718,
      120,
      208,
      342,
      240,
      90,
      700,
      1320,
      1456,
      336,
      1944,
      2220,
      792,
      120,
      1468,
      2208,
      560,
      48,
      680,
      120,
      738,
      1140,
      504,
      432,
      496,
      120,
      740,
      2244,
      168,
      180,
      144,
      3000,
      750,
      96,
      1000,
      84,
      100,
      144,
      1516,
      378,
      240,
      180,
      380,
      768,
      432,
      570,
      360,
      768,
      812,
      384,
      192,
      240,
      1032,
      1164,
      1548,
      264,
      300,
      588,
      304,
      1164,
      360,
      840,
      70,
      144,
      504,
      336,
      1580,
      1560,
      1576,
      396,
      176,
      780,
      304,
      120,
      420,
      2388,
      1080,
      66,
      228,
      144,
      288,
      1200,
      264,
      600,
      740,
      408,
      240,
      420,
      536,
      300,
      202,
      1080,
      270,
      336,
      1080,
      1140,
      1640,
      72,
      792,
      408,
      336,
      120,
      820,
      552,
      1648,
      624,
      200,
      1392,
      1656,
      48,
      276,
      840,
      1112,
      672,
      1008,
      552,
      1680,
      90,
      360,
      1254,
      838,
      240,
      406,
      84,
      56,
      42,
      1820,
      96,
      880,
      216,
      568,
      900,
      912,
      840,
      1708,
      240,
      360,
      72,
      1716,
      840,
      78,
      1320,
      80,
      1290,
      1728,
      144,
      1740,
      2604,
      1224,
      240,
      390,
      840,
      952,
      108,
      1176,
      144,
      2000,
      888,
      1756,
      438,
      1176,
      120,
      176,
      336,
      1768,
      252,
      1160,
      888,
      1776,
      456,
      256,
      660,
      1080,
      1344,
      288,
      888,
      1780,
      192,
      336,
      1344,
      210,
      600,
      108,
      120,
      176,
      228,
      180,
      600,
      1816,
      456,
      600,
      1680,
      70,
      72,
      840,
      2748,
      120,
      114,
      1040,
      72,
      102,
      240,
      88,
      138,
      140,
      240,
      1900,
      2784,
      624,
      336,
      928,
      120,
      1008,
      156,
      1240,
      936,
      180,
      168,
      1876,
      816,
      1256,
      480,
      470,
      1896,
      240,
      696,
      720,
      1320,
      1896,
      312,
      1036,
      900,
      1272,
      144,
      212,
      216,
      380,
      714,
      280,
      1434,
      1104,
      480,
      930,
      1596,
      72,
      240,
      1940,
      48,
      176,
      660,
      72,
      2940,
      970,
      648,
      368,
      2928,
      1400,
      120,
      652,
      984,
      220,
      1680,
      216,
      1470,
      1968,
      120,
      1980,
      252,
      32,
      252,
      528,
      120,
      198,
      240,
      440,
      1680,
      220,
      168,
      1996,
      498,
      1368,
      1500,
  };

  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    String[] strArray = s.nextLine().split(" ");

    long n = Long.valueOf(strArray[0]);
    int m = Integer.parseInt(strArray[1]);
    long newN = n % pisano[m - 2];
    long newFibRemainder = calc_fib_remainder(newN, m);
    System.out.println(newFibRemainder % m);
  }

  private static long calc_fib_remainder(long n, long base)
  {
    if (n <= 1) {
      return n;
    }
    long f0 = 0;
    long f1 = 1;
    long f2 = 1;
    for (int i = 2; i < n + 1; i++) {
      f2 = (f0 + f1) % (base * 10);
      f0 = f1;
      f1 = f2;
    }
    return f2;
  }
}
