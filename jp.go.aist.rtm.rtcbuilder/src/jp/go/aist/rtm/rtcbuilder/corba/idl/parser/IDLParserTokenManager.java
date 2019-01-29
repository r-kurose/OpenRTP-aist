package jp.go.aist.rtm.rtcbuilder.corba.idl.parser;

public class IDLParserTokenManager implements IDLParserConstants
{
  public  java.io.PrintStream debugStream = System.out;
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x20000000L) != 0L)
            return 79;
         if ((active0 & 0xf99ffffc00089000L) != 0L || (active1 & 0x3fL) != 0L)
         {
            jjmatchedKind = 70;
            return 13;
         }
         return -1;
      case 1:
         if ((active0 & 0x8000L) != 0L || (active1 & 0xaL) != 0L)
            return 13;
         if ((active0 & 0xf99ffffc00081000L) != 0L || (active1 & 0x35L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 70;
               jjmatchedPos = 1;
            }
            return 13;
         }
         return -1;
      case 2:
         if ((active0 & 0xf99fdffc00089000L) != 0L || (active1 & 0x39L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 2;
            return 13;
         }
         if ((active0 & 0x200000000000L) != 0L || (active1 & 0x4L) != 0L)
            return 13;
         return -1;
      case 3:
         if ((active0 & 0xa048400000000L) != 0L || (active1 & 0x1L) != 0L)
            return 13;
         if ((active0 & 0xf995db7800089000L) != 0L || (active1 & 0x38L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 3;
            return 13;
         }
         return -1;
      case 4:
         if ((active0 & 0x912800080000L) != 0L || (active1 & 0x8L) != 0L)
            return 13;
         if ((active0 & 0xf9954a5000009000L) != 0L || (active1 & 0x30L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 4;
            return 13;
         }
         return -1;
      case 5:
         if ((active0 & 0x79140a1000008000L) != 0L || (active1 & 0x20L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 5;
            return 13;
         }
         if ((active0 & 0x8081404000001000L) != 0L || (active1 & 0x10L) != 0L)
            return 13;
         return -1;
      case 6:
         if ((active0 & 0x7810020000008000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 6;
            return 13;
         }
         if ((active0 & 0x104081000000000L) != 0L || (active1 & 0x20L) != 0L)
            return 13;
         return -1;
      case 7:
         if ((active0 & 0x810020000000000L) != 0L)
            return 13;
         if ((active0 & 0x7000000000008000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 7;
            return 13;
         }
         return -1;
      case 8:
         if ((active0 & 0x7000000000008000L) != 0L)
            return 13;
         return -1;
      case 9:
         if ((active0 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 9;
            return 13;
         }
         return -1;
      case 10:
         if ((active0 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 10;
            return 13;
         }
         return -1;
      case 11:
         if ((active0 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 11;
            return 13;
         }
         return -1;
      case 12:
         if ((active0 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 70;
            jjmatchedPos = 12;
            return 13;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private final int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private final int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private final int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 37:
         return jjStopAtPos(0, 30);
      case 38:
         return jjStopAtPos(0, 23);
      case 40:
         return jjStopAtPos(0, 32);
      case 41:
         return jjStopAtPos(0, 33);
      case 42:
         return jjStopAtPos(0, 28);
      case 43:
         return jjStopAtPos(0, 26);
      case 44:
         return jjStopAtPos(0, 17);
      case 45:
         return jjStopAtPos(0, 27);
      case 47:
         return jjStartNfaWithStates_0(0, 29, 79);
      case 58:
         jjmatchedKind = 16;
         return jjMoveStringLiteralDfa1_0(0x40000L, 0x0L);
      case 59:
         return jjStopAtPos(0, 11);
      case 60:
         jjmatchedKind = 53;
         return jjMoveStringLiteralDfa1_0(0x2000000L, 0x0L);
      case 61:
         return jjStopAtPos(0, 20);
      case 62:
         jjmatchedKind = 54;
         return jjMoveStringLiteralDfa1_0(0x1000000L, 0x0L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x800000000L, 0x0L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x400000000L, 0x0L);
      case 91:
         return jjStopAtPos(0, 57);
      case 93:
         return jjStopAtPos(0, 58);
      case 94:
         return jjStopAtPos(0, 22);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x1000200000000000L, 0x0L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x80000000000L, 0x0L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x2040000080000L, 0x20L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x4004000000000L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x6008000000000000L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x2000000000L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x8000L, 0xaL);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x8000000000L, 0x0L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x1000L, 0x0L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x8000100000000000L, 0x4L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x800000000000000L, 0x10L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x91410000000000L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1000000000L, 0x0L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x820000000000L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x100000000000000L, 0x0L);
      case 123:
         return jjStopAtPos(0, 13);
      case 124:
         return jjStopAtPos(0, 21);
      case 125:
         return jjStopAtPos(0, 14);
      case 126:
         return jjStopAtPos(0, 31);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private final int jjMoveStringLiteralDfa1_0(long active0, long active1)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1);
      return 1;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         break;
      case 60:
         if ((active0 & 0x2000000L) != 0L)
            return jjStopAtPos(1, 25);
         break;
      case 62:
         if ((active0 & 0x1000000L) != 0L)
            return jjStopAtPos(1, 24);
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L, active1, 0L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000000L, active1, 0x10L);
      case 99:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x814000000000000L, active1, 0L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x50000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000L, active1, 0L);
      case 110:
         if ((active1 & 0x2L) != 0L)
         {
            jjmatchedKind = 65;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x8008a20000008000L, active1, 0x8L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x8c000081000L, active1, 0x21L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000000000L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x1080400000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x4L);
      case 119:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x6000000000000000L, active1, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1);
}
private final int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(0, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1);
      return 2;
   }
   switch(curChar)
   {
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L, active1, 0L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x800040000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x6000000000000000L, active1, 0L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000000000L, active1, 0L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x1800000000000L, active1, 0x11L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000080000L, active1, 0x20L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x92000000000L, active1, 0x8L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L, active1, 0L);
      case 113:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x80400000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x2020000000000L, active1, 0L);
      case 116:
         if ((active1 & 0x4L) != 0L)
            return jjStartNfaWithStates_0(2, 66, 13);
         return jjMoveStringLiteralDfa3_0(active0, 0x1100100000008000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x8004000000000L, active1, 0L);
      case 121:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 45, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1);
}
private final int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 69:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(3, 34, 13);
         break;
      case 83:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x4002000000000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000L, active1, 0L);
      case 100:
         if ((active1 & 0x1L) != 0L)
            return jjStartNfaWithStates_0(3, 64, 13);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 49, 13);
         return jjMoveStringLiteralDfa4_0(active0, 0x6000101000008000L, active1, 0L);
      case 103:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 13);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x80020000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000000L, active1, 0L);
      case 109:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 51, 13);
         break;
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000000L, active1, 0L);
      case 114:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 42, 13);
         return jjMoveStringLiteralDfa4_0(active0, 0x1100010000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L, active1, 0x10L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000000000L, active1, 0x20L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x10400000001000L, active1, 0x8L);
      case 119:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}
private final int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, active1);
      return 4;
   }
   switch(curChar)
   {
      case 69:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(4, 35, 13);
         break;
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x1400000000000L, active1, 0L);
      case 100:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x10080000000000L, active1, 0x30L);
      case 103:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x1100000000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000001000L, active1, 0L);
      case 110:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 47, 13);
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000000000L, active1, 0L);
      case 112:
         return jjMoveStringLiteralDfa5_0(active0, 0x6000000000000000L, active1, 0L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000L, active1, 0L);
      case 116:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(4, 19, 13);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 37, 13);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 40, 13);
         else if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 44, 13);
         else if ((active1 & 0x8L) != 0L)
            return jjStartNfaWithStates_0(4, 67, 13);
         break;
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, active1);
}
private final int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(3, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, active1);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x80000000000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(5, 12, 13);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 38, 13);
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000L, active1, 0L);
      case 102:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L, active1, 0L);
      case 103:
         if ((active0 & 0x80000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 55, 13);
         break;
      case 104:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 48, 13);
         break;
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x910020000000000L, active1, 0L);
      case 115:
         if ((active1 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(5, 68, 13);
         break;
      case 116:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 46, 13);
         return jjMoveStringLiteralDfa6_0(active0, 0x6000000000000000L, active1, 0L);
      case 120:
         return jjMoveStringLiteralDfa6_0(active0, 0L, active1, 0x20L);
      case 121:
         if ((active0 & 0x8000000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 63, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0, active1);
}
private final int jjMoveStringLiteralDfa6_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(4, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, active1);
      return 6;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000000000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa7_0(active0, 0x20000000000L, active1, 0L);
      case 102:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 36, 13);
         break;
      case 103:
         if ((active0 & 0x100000000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 56, 13);
         break;
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x6000000000000000L, active1, 0L);
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000000000L, active1, 0L);
      case 110:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 43, 13);
         break;
      case 116:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 50, 13);
         else if ((active1 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(6, 69, 13);
         break;
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0, active1);
}
private final int jjMoveStringLiteralDfa7_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(5, old0, old1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000L);
      case 100:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 41, 13);
         break;
      case 101:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 52, 13);
         break;
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x6000000000000000L);
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000000000000000L);
      case 121:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 59, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private final int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(8, 15, 13);
         else if ((active0 & 0x1000000000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 60, 13);
         break;
      case 110:
         if ((active0 & 0x2000000000000000L) != 0L)
         {
            jjmatchedKind = 61;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L);
}
private final int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 95:
         return jjMoveStringLiteralDfa10_0(active0, 0x4000000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L);
}
private final int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, 0L);
      return 10;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa11_0(active0, 0x4000000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, 0L);
}
private final int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0, 0L);
      return 11;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa12_0(active0, 0x4000000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0, 0L);
}
private final int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0, 0L);
      return 12;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa13_0(active0, 0x4000000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(11, active0, 0L);
}
private final int jjMoveStringLiteralDfa13_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(11, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(12, active0, 0L);
      return 13;
   }
   switch(curChar)
   {
      case 121:
         if ((active0 & 0x4000000000000000L) != 0L)
            return jjStartNfaWithStates_0(13, 62, 13);
         break;
      default :
         break;
   }
   return jjStartNfa_0(12, active0, 0L);
}
private final void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private final void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private final void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}
private final void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}
private final void jjCheckNAddStates(int start)
{
   jjCheckNAdd(jjnextStates[start]);
   jjCheckNAdd(jjnextStates[start + 1]);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private final int jjMoveNfa_0(int startState, int curPos)
{
   int[] nextStates;
   int startsAt = 0;
   jjnewStateCnt = 98;
   int i = 1;
   jjstateSet[0] = startState;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 5);
                  else if (curChar == 47)
                     jjAddStates(6, 7);
                  else if (curChar == 35)
                     jjAddStates(8, 10);
                  else if (curChar == 34)
                     jjCheckNAddStates(11, 13);
                  else if (curChar == 39)
                     jjAddStates(14, 15);
                  else if (curChar == 46)
                     jjCheckNAdd(21);
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 72)
                        kind = 72;
                     jjCheckNAddTwoStates(18, 19);
                  }
                  else if (curChar == 48)
                     jjAddStates(16, 17);
                  else if (curChar == 35)
                     jjCheckNAddTwoStates(1, 2);
                  if (curChar == 48)
                  {
                     if (kind > 71)
                        kind = 71;
                     jjCheckNAddTwoStates(15, 16);
                  }
                  break;
               case 79:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(83, 84);
                  else if (curChar == 47)
                     jjCheckNAddTwoStates(80, 81);
                  break;
               case 1:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(18, 21);
                  break;
               case 3:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddTwoStates(3, 4);
                  break;
               case 4:
                  if (curChar == 34)
                     jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(5, 6);
                  break;
               case 6:
                  if (curChar == 34)
                     jjCheckNAddStates(22, 24);
                  break;
               case 7:
                  if (curChar == 10 && kind > 10)
                     kind = 10;
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(25, 28);
                  break;
               case 9:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddStates(29, 31);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(10, 7);
                  break;
               case 11:
                  if ((0x100000200L & l) != 0L)
                     jjCheckNAddStates(32, 36);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 70)
                     kind = 70;
                  jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 14:
                  if (curChar != 48)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(15, 16);
                  break;
               case 15:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(15, 16);
                  break;
               case 17:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 72)
                     kind = 72;
                  jjCheckNAddTwoStates(18, 19);
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 72)
                     kind = 72;
                  jjCheckNAddTwoStates(18, 19);
                  break;
               case 20:
                  if (curChar == 46)
                     jjCheckNAdd(21);
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 74)
                     kind = 74;
                  jjCheckNAddStates(37, 39);
                  break;
               case 23:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(24);
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 74)
                     kind = 74;
                  jjCheckNAddTwoStates(24, 25);
                  break;
               case 26:
                  if (curChar == 39)
                     jjAddStates(14, 15);
                  break;
               case 27:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(28);
                  break;
               case 28:
                  if (curChar == 39 && kind > 76)
                     kind = 76;
                  break;
               case 30:
                  if ((0x8000008400000000L & l) != 0L)
                     jjCheckNAdd(28);
                  break;
               case 31:
                  if (curChar == 48)
                     jjCheckNAddTwoStates(32, 28);
                  break;
               case 32:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(32, 28);
                  break;
               case 33:
                  if ((0x3fe000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(34, 28);
                  break;
               case 34:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(34, 28);
                  break;
               case 35:
                  if (curChar == 48)
                     jjAddStates(40, 41);
                  break;
               case 37:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(37, 28);
                  break;
               case 39:
                  if (curChar == 34)
                     jjCheckNAddStates(11, 13);
                  break;
               case 40:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 42:
                  if ((0x8000008400000000L & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 43:
                  if (curChar == 34 && kind > 77)
                     kind = 77;
                  break;
               case 44:
                  if (curChar == 48)
                     jjCheckNAddStates(42, 45);
                  break;
               case 45:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(42, 45);
                  break;
               case 46:
                  if ((0x3fe000000000000L & l) != 0L)
                     jjCheckNAddStates(46, 49);
                  break;
               case 47:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(46, 49);
                  break;
               case 48:
                  if (curChar == 48)
                     jjAddStates(50, 51);
                  break;
               case 50:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(52, 55);
                  break;
               case 52:
                  if (curChar == 48)
                     jjAddStates(16, 17);
                  break;
               case 54:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 73)
                     kind = 73;
                  jjAddStates(56, 57);
                  break;
               case 57:
                  if (curChar == 35)
                     jjAddStates(8, 10);
                  break;
               case 59:
                  if (kind > 5)
                     kind = 5;
                  jjstateSet[jjnewStateCnt++] = 59;
                  break;
               case 66:
                  if (kind > 6)
                     kind = 6;
                  jjstateSet[jjnewStateCnt++] = 66;
                  break;
               case 73:
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 73;
                  break;
               case 78:
                  if (curChar == 47)
                     jjAddStates(6, 7);
                  break;
               case 80:
                  if ((0xfffffffffffffbffL & l) != 0L)
                     jjCheckNAddTwoStates(80, 81);
                  break;
               case 81:
                  if (curChar == 10 && kind > 8)
                     kind = 8;
                  break;
               case 82:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 83:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 84:
                  if (curChar == 42)
                     jjAddStates(58, 59);
                  break;
               case 85:
                  if ((0xffff7fffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(86, 84);
                  break;
               case 86:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(86, 84);
                  break;
               case 87:
                  if (curChar == 47 && kind > 9)
                     kind = 9;
                  break;
               case 88:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 5);
                  break;
               case 89:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(89, 90);
                  break;
               case 90:
                  if (curChar != 46)
                     break;
                  if (kind > 74)
                     kind = 74;
                  jjCheckNAddStates(60, 62);
                  break;
               case 91:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 74)
                     kind = 74;
                  jjCheckNAddStates(60, 62);
                  break;
               case 92:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(92, 20);
                  break;
               case 93:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(93, 94);
                  break;
               case 95:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(96);
                  break;
               case 96:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 75)
                     kind = 75;
                  jjCheckNAddTwoStates(96, 97);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 13:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 70)
                     kind = 70;
                  jjCheckNAdd(13);
                  break;
               case 5:
                  jjAddStates(63, 64);
                  break;
               case 16:
                  if ((0x20100000201000L & l) != 0L && kind > 71)
                     kind = 71;
                  break;
               case 19:
                  if ((0x20100000201000L & l) != 0L && kind > 72)
                     kind = 72;
                  break;
               case 22:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(65, 66);
                  break;
               case 25:
                  if ((0x104000001040L & l) != 0L && kind > 74)
                     kind = 74;
                  break;
               case 27:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(28);
                  break;
               case 29:
                  if (curChar == 92)
                     jjAddStates(67, 70);
                  break;
               case 30:
                  if ((0x54404610000000L & l) != 0L)
                     jjCheckNAdd(28);
                  break;
               case 36:
                  if (curChar == 120)
                     jjCheckNAdd(37);
                  break;
               case 37:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStates(37, 28);
                  break;
               case 38:
                  if (curChar == 88)
                     jjCheckNAdd(37);
                  break;
               case 40:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 41:
                  if (curChar == 92)
                     jjAddStates(71, 74);
                  break;
               case 42:
                  if ((0x54404610000000L & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 49:
                  if (curChar == 120)
                     jjCheckNAdd(50);
                  break;
               case 50:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStates(52, 55);
                  break;
               case 51:
                  if (curChar == 88)
                     jjCheckNAdd(50);
                  break;
               case 53:
                  if (curChar == 120)
                     jjCheckNAdd(54);
                  break;
               case 54:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 73)
                     kind = 73;
                  jjCheckNAddTwoStates(54, 55);
                  break;
               case 55:
                  if ((0x20100000201000L & l) != 0L && kind > 73)
                     kind = 73;
                  break;
               case 56:
                  if (curChar == 88)
                     jjCheckNAdd(54);
                  break;
               case 58:
                  if (curChar != 102)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAdd(59);
                  break;
               case 59:
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAdd(59);
                  break;
               case 60:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 58;
                  break;
               case 61:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 60;
                  break;
               case 62:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 61;
                  break;
               case 63:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 62;
                  break;
               case 64:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 63;
                  break;
               case 65:
                  if (curChar != 101)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAdd(66);
                  break;
               case 66:
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAdd(66);
                  break;
               case 67:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 65;
                  break;
               case 68:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 67;
                  break;
               case 69:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 68;
                  break;
               case 70:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 69;
                  break;
               case 71:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 70;
                  break;
               case 72:
                  if (curChar != 102)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(73);
                  break;
               case 73:
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(73);
                  break;
               case 74:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 72;
                  break;
               case 75:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 74;
                  break;
               case 76:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 75;
                  break;
               case 77:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 76;
                  break;
               case 80:
                  jjAddStates(75, 76);
                  break;
               case 83:
                  jjCheckNAddTwoStates(83, 84);
                  break;
               case 85:
               case 86:
                  jjCheckNAddTwoStates(86, 84);
                  break;
               case 94:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(77, 78);
                  break;
               case 97:
                  if ((0x104000001040L & l) != 0L && kind > 75)
                     kind = 75;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 5:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(63, 64);
                  break;
               case 27:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 40:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(11, 13);
                  break;
               case 59:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjstateSet[jjnewStateCnt++] = 59;
                  break;
               case 66:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjstateSet[jjnewStateCnt++] = 66;
                  break;
               case 73:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjstateSet[jjnewStateCnt++] = 73;
                  break;
               case 80:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(75, 76);
                  break;
               case 83:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 85:
               case 86:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(86, 84);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 98 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   89, 90, 92, 20, 93, 94, 79, 82, 64, 71, 77, 40, 41, 43, 27, 29,
   53, 56, 2, 3, 4, 7, 7, 8, 11, 9, 10, 7, 8, 9, 10, 7,
   9, 10, 7, 8, 11, 21, 22, 25, 36, 38, 40, 41, 45, 43, 40, 41,
   47, 43, 49, 51, 40, 41, 50, 43, 54, 55, 85, 87, 91, 22, 25, 5,
   6, 23, 24, 30, 31, 33, 35, 42, 44, 46, 48, 80, 81, 95, 96,
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, "\73",
"\155\157\144\165\154\145", "\173", "\175", "\151\156\164\145\162\146\141\143\145", "\72", "\54",
"\72\72", "\143\157\156\163\164", "\75", "\174", "\136", "\46", "\76\76", "\74\74",
"\53", "\55", "\52", "\57", "\45", "\176", "\50", "\51", "\124\122\125\105",
"\106\101\114\123\105", "\164\171\160\145\144\145\146", "\146\154\157\141\164",
"\144\157\165\142\154\145", "\154\157\156\147", "\163\150\157\162\164",
"\165\156\163\151\147\156\145\144", "\143\150\141\162", "\142\157\157\154\145\141\156", "\157\143\164\145\164",
"\141\156\171", "\163\164\162\165\143\164", "\165\156\151\157\156",
"\163\167\151\164\143\150", "\143\141\163\145", "\144\145\146\141\165\154\164", "\145\156\165\155",
"\163\145\161\165\145\156\143\145", "\74", "\76", "\163\164\162\151\156\147", "\167\163\164\162\151\156\147",
"\133", "\135", "\162\145\141\144\157\156\154\171",
"\141\164\164\162\151\142\165\164\145", "\145\170\143\145\160\164\151\157\156",
"\145\170\143\145\160\164\151\157\156\137\142\157\144\171", "\157\156\145\167\141\171", "\166\157\151\144", "\151\156", "\157\165\164",
"\151\156\157\165\164", "\162\141\151\163\145\163", "\143\157\156\164\145\170\164", null, null, null,
null, null, null, null, null, };
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffffffffffff801L, 0x3fffL,
};
static final long[] jjtoSkip = {
   0x7feL, 0x0L,
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[98];
private final int[] jjstateSet = new int[196];
protected char curChar;
public IDLParserTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}
public IDLParserTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private final void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 98; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   Token t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   String im = jjstrLiteralImages[jjmatchedKind];
   t.image = (im == null) ? input_stream.GetImage() : im;
   t.beginLine = input_stream.getBeginLine();
   t.beginColumn = input_stream.getBeginColumn();
   t.endLine = input_stream.getEndLine();
   t.endColumn = input_stream.getEndColumn();
   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

public Token getNextToken()
{
  int kind;
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

}
