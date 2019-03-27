  /*
    2    * Copyright (c) 1995, 2000, Oracle and/or its affiliates. All rights reserved.
    3    * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
    4    *
    5    * This code is free software; you can redistribute it and/or modify it
    6    * under the terms of the GNU General Public License version 2 only, as
    7    * published by the Free Software Foundation.  Oracle designates this
    8    * particular file as subject to the "Classpath" exception as provided
    9    * by Oracle in the LICENSE file that accompanied this code.
   10    *
   11    * This code is distributed in the hope that it will be useful, but WITHOUT
   12    * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
   13    * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
   14    * version 2 for more details (a copy is included in the LICENSE file that
   15    * accompanied this code).
   16    *
   17    * You should have received a copy of the GNU General Public License version
   18    * 2 along with this work; if not, write to the Free Software Foundation,
   19    * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
   20    *
   21    * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
   22    * or visit www.oracle.com if you need additional information or have any
   23    * questions.
   24    */
package com.wx.rsamaster.secret.base;

 import java.io.OutputStream;
import java.io.PushbackInputStream;


  public class BASE64Decoder extends CharacterDecoder {
 
/** This class has 4 bytes per atom */
 protected int bytesPerAtom() {
      return (4);
    }
 
    /** Any multiple of 4 will do, 72 might be common */
  protected int bytesPerLine() {
      return (72);
    }

      private final static char pem_array[] = {
         //       0   1   2   3   4   5   6   7
                    'A','B','C','D','E','F','G','H', // 0
                  'I','J','K','L','M','N','O','P', // 1
                'Q','R','S','T','U','V','W','X', // 2
                 'Y','Z','a','b','c','d','e','f', // 3
                'g','h','i','j','k','l','m','n', // 4
               'o','p','q','r','s','t','u','v', // 5
                  'w','x','y','z','0','1','2','3', // 6
                '4','5','6','7','8','9','+','/'  // 7
        };
 
    private final static byte pem_convert_array[] = new byte[256];

    static {
     for (int i = 0; i < 255; i++) {
          pem_convert_array[i] = -1;
      }
       for (int i = 0; i < pem_array.length; i++) {
          pem_convert_array[pem_array[i]] = (byte) i;
     }
     }
   byte decode_buffer[] = new byte[4];
   /**
  103        * Decode one BASE64 atom into 1, 2, or 3 bytes of data.
  104        */
  protected void decodeAtom(PushbackInputStream inStream, OutputStream outStream, int rem)
     throws java.io.IOException
   {
        int     i;
      byte    a = -1, b = -1, c = -1, d = -1;

     if (rem < 2) {
           throw new CEFormatException("BASE64Decoder: Not enough bytes for an atom.");
       }
        do {
          i = inStream.read();
           if (i == -1) {
              throw new CEStreamExhausted();
              }
        } while (i == '\n' || i == '\r');
        decode_buffer[0] = (byte) i;

     i = readFully(inStream, decode_buffer, 1, rem-1);
     if (i == -1) {
       throw new CEStreamExhausted();
      }

        if (rem > 3 && decode_buffer[3] == '=') {
            rem = 3;
         }
        if (rem > 2 && decode_buffer[2] == '=') {
            rem = 2;
       }
      switch (rem) {
       case 4:
          d = pem_convert_array[decode_buffer[3] & 0xff];
         // NOBREAK
      case 3:
           c = pem_convert_array[decode_buffer[2] & 0xff];
       // NOBREAK
    case 2:
        b = pem_convert_array[decode_buffer[1] & 0xff];
       a = pem_convert_array[decode_buffer[0] & 0xff];
            break;
       }

       switch (rem) {
        case 2:
          outStream.write( (byte)(((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
          break;
      case 3:
             outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
            outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
            break;
        case 4:
           outStream.write( (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)) );
            outStream.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
         outStream.write( (byte) (((c << 6) & 0xc0) | (d  & 0x3f)) );
       break;
 }
      return;
   }
 }