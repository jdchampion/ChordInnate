package chordinnate.musictheory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static chordinnate.musictheory.PitchClass.*;
import static chordinnate.musictheory.Octave.*;

/**
 * Created by Joseph on 4/18/16.
 */
public enum Pitch
        implements Transposable<Pitch>, Enharmonic<Pitch>, Diatonic {

    // Cbb10 is out of playable MIDI range, so it has been removed
    C_DOUBLE_FLAT_0(C_DOUBLE_FLAT, OCTAVE_0),                                   // 0
    C_DOUBLE_FLAT_1(C_DOUBLE_FLAT, OCTAVE_1),                                   // 1
    C_DOUBLE_FLAT_2(C_DOUBLE_FLAT, OCTAVE_2),                                   // 2
    C_DOUBLE_FLAT_3(C_DOUBLE_FLAT, OCTAVE_3),                                   // 3
    C_DOUBLE_FLAT_4(C_DOUBLE_FLAT, OCTAVE_4),                                   // 4
    C_DOUBLE_FLAT_5(C_DOUBLE_FLAT, OCTAVE_5),                                   // 5
    C_DOUBLE_FLAT_6(C_DOUBLE_FLAT, OCTAVE_6),                                   // 6
    C_DOUBLE_FLAT_7(C_DOUBLE_FLAT, OCTAVE_7),                                   // 7
    C_DOUBLE_FLAT_8(C_DOUBLE_FLAT, OCTAVE_8),                                   // 8
    C_DOUBLE_FLAT_9(C_DOUBLE_FLAT, OCTAVE_9),                                   // 9

    // Cb10 is out of playable MIDI range, so it has been removed
    C_FLAT_0(C_FLAT, OCTAVE_0),                                                 // 10
    C_FLAT_1(C_FLAT, OCTAVE_1),                                                 // 11
    C_FLAT_2(C_FLAT, OCTAVE_2),                                                 // 12
    C_FLAT_3(C_FLAT, OCTAVE_3),                                                 // 13
    C_FLAT_4(C_FLAT, OCTAVE_4),                                                 // 14
    C_FLAT_5(C_FLAT, OCTAVE_5),                                                 // 15
    C_FLAT_6(C_FLAT, OCTAVE_6),                                                 // 16
    C_FLAT_7(C_FLAT, OCTAVE_7),                                                 // 17
    C_FLAT_8(C_FLAT, OCTAVE_8),                                                 // 18
    C_FLAT_9(C_FLAT, OCTAVE_9),                                                 // 19

    C_0(C, OCTAVE_0),                                                           // 20
    C_1(C, OCTAVE_1),                                                           // 21
    C_2(C, OCTAVE_2),                                                           // 22
    C_3(C, OCTAVE_3),                                                           // 23
    C_4(C, OCTAVE_4),                                                           // 24
    C_5(C, OCTAVE_5),                                                           // 25
    C_6(C, OCTAVE_6),                                                           // 26
    C_7(C, OCTAVE_7),                                                           // 27
    C_8(C, OCTAVE_8),                                                           // 28
    C_9(C, OCTAVE_9),                                                           // 29
    C_10(C, OCTAVE_10),                                                         // 30

    C_SHARP_0(C_SHARP, OCTAVE_0),                                               // 31
    C_SHARP_1(C_SHARP, OCTAVE_1),                                               // 32
    C_SHARP_2(C_SHARP, OCTAVE_2),                                               // 33
    C_SHARP_3(C_SHARP, OCTAVE_3),                                               // 34
    C_SHARP_4(C_SHARP, OCTAVE_4),                                               // 35
    C_SHARP_5(C_SHARP, OCTAVE_5),                                               // 36
    C_SHARP_6(C_SHARP, OCTAVE_6),                                               // 37
    C_SHARP_7(C_SHARP, OCTAVE_7),                                               // 38
    C_SHARP_8(C_SHARP, OCTAVE_8),                                               // 39
    C_SHARP_9(C_SHARP, OCTAVE_9),                                               // 40
    C_SHARP_10(C_SHARP, OCTAVE_10),                                             // 41

    C_DOUBLE_SHARP_0(C_DOUBLE_SHARP, OCTAVE_0),                                 // 42
    C_DOUBLE_SHARP_1(C_DOUBLE_SHARP, OCTAVE_1),                                 // 43
    C_DOUBLE_SHARP_2(C_DOUBLE_SHARP, OCTAVE_2),                                 // 44
    C_DOUBLE_SHARP_3(C_DOUBLE_SHARP, OCTAVE_3),                                 // 45
    C_DOUBLE_SHARP_4(C_DOUBLE_SHARP, OCTAVE_4),                                 // 46
    C_DOUBLE_SHARP_5(C_DOUBLE_SHARP, OCTAVE_5),                                 // 47
    C_DOUBLE_SHARP_6(C_DOUBLE_SHARP, OCTAVE_6),                                 // 48
    C_DOUBLE_SHARP_7(C_DOUBLE_SHARP, OCTAVE_7),                                 // 49
    C_DOUBLE_SHARP_8(C_DOUBLE_SHARP, OCTAVE_8),                                 // 50
    C_DOUBLE_SHARP_9(C_DOUBLE_SHARP, OCTAVE_9),                                 // 51
    C_DOUBLE_SHARP_10(C_DOUBLE_SHARP, OCTAVE_10),                               // 52



    D_DOUBLE_FLAT_0(D_DOUBLE_FLAT, OCTAVE_0),                                   // 53
    D_DOUBLE_FLAT_1(D_DOUBLE_FLAT, OCTAVE_1),                                   // 54
    D_DOUBLE_FLAT_2(D_DOUBLE_FLAT, OCTAVE_2),                                   // 55
    D_DOUBLE_FLAT_3(D_DOUBLE_FLAT, OCTAVE_3),                                   // 56
    D_DOUBLE_FLAT_4(D_DOUBLE_FLAT, OCTAVE_4),                                   // 57
    D_DOUBLE_FLAT_5(D_DOUBLE_FLAT, OCTAVE_5),                                   // 58
    D_DOUBLE_FLAT_6(D_DOUBLE_FLAT, OCTAVE_6),                                   // 59
    D_DOUBLE_FLAT_7(D_DOUBLE_FLAT, OCTAVE_7),                                   // 60
    D_DOUBLE_FLAT_8(D_DOUBLE_FLAT, OCTAVE_8),                                   // 61
    D_DOUBLE_FLAT_9(D_DOUBLE_FLAT, OCTAVE_9),                                   // 62
    D_DOUBLE_FLAT_10(D_DOUBLE_FLAT, OCTAVE_10),                                 // 63

    D_FLAT_0(D_FLAT, OCTAVE_0),                                                 // 64
    D_FLAT_1(D_FLAT, OCTAVE_1),                                                 // 65
    D_FLAT_2(D_FLAT, OCTAVE_2),                                                 // 66
    D_FLAT_3(D_FLAT, OCTAVE_3),                                                 // 67
    D_FLAT_4(D_FLAT, OCTAVE_4),                                                 // 68
    D_FLAT_5(D_FLAT, OCTAVE_5),                                                 // 69
    D_FLAT_6(D_FLAT, OCTAVE_6),                                                 // 70
    D_FLAT_7(D_FLAT, OCTAVE_7),                                                 // 71
    D_FLAT_8(D_FLAT, OCTAVE_8),                                                 // 72
    D_FLAT_9(D_FLAT, OCTAVE_9),                                                 // 73
    D_FLAT_10(D_FLAT, OCTAVE_10),                                               // 74

    D_0(D, OCTAVE_0),                                                           // 75
    D_1(D, OCTAVE_1),                                                           // 76
    D_2(D, OCTAVE_2),                                                           // 77
    D_3(D, OCTAVE_3),                                                           // 78
    D_4(D, OCTAVE_4),                                                           // 79
    D_5(D, OCTAVE_5),                                                           // 80
    D_6(D, OCTAVE_6),                                                           // 81
    D_7(D, OCTAVE_7),                                                           // 82
    D_8(D, OCTAVE_8),                                                           // 83
    D_9(D, OCTAVE_9),                                                           // 84
    D_10(D, OCTAVE_10),                                                         // 85

    D_SHARP_0(D_SHARP, OCTAVE_0),                                               // 86
    D_SHARP_1(D_SHARP, OCTAVE_1),                                               // 87
    D_SHARP_2(D_SHARP, OCTAVE_2),                                               // 88
    D_SHARP_3(D_SHARP, OCTAVE_3),                                               // 89
    D_SHARP_4(D_SHARP, OCTAVE_4),                                               // 90
    D_SHARP_5(D_SHARP, OCTAVE_5),                                               // 91
    D_SHARP_6(D_SHARP, OCTAVE_6),                                               // 92
    D_SHARP_7(D_SHARP, OCTAVE_7),                                               // 93
    D_SHARP_8(D_SHARP, OCTAVE_8),                                               // 94
    D_SHARP_9(D_SHARP, OCTAVE_9),                                               // 95
    D_SHARP_10(D_SHARP, OCTAVE_10),                                             // 96

    D_DOUBLE_SHARP_0(D_DOUBLE_SHARP, OCTAVE_0),                                 // 97
    D_DOUBLE_SHARP_1(D_DOUBLE_SHARP, OCTAVE_1),                                 // 98
    D_DOUBLE_SHARP_2(D_DOUBLE_SHARP, OCTAVE_2),                                 // 99
    D_DOUBLE_SHARP_3(D_DOUBLE_SHARP, OCTAVE_3),                                 // 100
    D_DOUBLE_SHARP_4(D_DOUBLE_SHARP, OCTAVE_4),                                 // 101
    D_DOUBLE_SHARP_5(D_DOUBLE_SHARP, OCTAVE_5),                                 // 102
    D_DOUBLE_SHARP_6(D_DOUBLE_SHARP, OCTAVE_6),                                 // 103
    D_DOUBLE_SHARP_7(D_DOUBLE_SHARP, OCTAVE_7),                                 // 104
    D_DOUBLE_SHARP_8(D_DOUBLE_SHARP, OCTAVE_8),                                 // 105
    D_DOUBLE_SHARP_9(D_DOUBLE_SHARP, OCTAVE_9),                                 // 106
    D_DOUBLE_SHARP_10(D_DOUBLE_SHARP, OCTAVE_10),                               // 107
    
    
    

    E_DOUBLE_FLAT_0(E_DOUBLE_FLAT, OCTAVE_0),                                   // 108
    E_DOUBLE_FLAT_1(E_DOUBLE_FLAT, OCTAVE_1),                                   // 109
    E_DOUBLE_FLAT_2(E_DOUBLE_FLAT, OCTAVE_2),                                   // 110
    E_DOUBLE_FLAT_3(E_DOUBLE_FLAT, OCTAVE_3),                                   // 111
    E_DOUBLE_FLAT_4(E_DOUBLE_FLAT, OCTAVE_4),                                   // 112
    E_DOUBLE_FLAT_5(E_DOUBLE_FLAT, OCTAVE_5),                                   // 113
    E_DOUBLE_FLAT_6(E_DOUBLE_FLAT, OCTAVE_6),                                   // 114
    E_DOUBLE_FLAT_7(E_DOUBLE_FLAT, OCTAVE_7),                                   // 115
    E_DOUBLE_FLAT_8(E_DOUBLE_FLAT, OCTAVE_8),                                   // 116
    E_DOUBLE_FLAT_9(E_DOUBLE_FLAT, OCTAVE_9),                                   // 117
    E_DOUBLE_FLAT_10(E_DOUBLE_FLAT, OCTAVE_10),                                 // 118

    E_FLAT_0(E_FLAT, OCTAVE_0),                                                 // 119
    E_FLAT_1(E_FLAT, OCTAVE_1),                                                 // 120
    E_FLAT_2(E_FLAT, OCTAVE_2),                                                 // 121
    E_FLAT_3(E_FLAT, OCTAVE_3),                                                 // 122
    E_FLAT_4(E_FLAT, OCTAVE_4),                                                 // 123
    E_FLAT_5(E_FLAT, OCTAVE_5),                                                 // 124
    E_FLAT_6(E_FLAT, OCTAVE_6),                                                 // 125
    E_FLAT_7(E_FLAT, OCTAVE_7),                                                 // 126
    E_FLAT_8(E_FLAT, OCTAVE_8),                                                 // 127
    E_FLAT_9(E_FLAT, OCTAVE_9),                                                 // 128
    E_FLAT_10(E_FLAT, OCTAVE_10),                                               // 129

    E_0(E, OCTAVE_0),                                                           // 130
    E_1(E, OCTAVE_1),                                                           // 131
    E_2(E, OCTAVE_2),                                                           // 132
    E_3(E, OCTAVE_3),                                                           // 133
    E_4(E, OCTAVE_4),                                                           // 134
    E_5(E, OCTAVE_5),                                                           // 135
    E_6(E, OCTAVE_6),                                                           // 136
    E_7(E, OCTAVE_7),                                                           // 137
    E_8(E, OCTAVE_8),                                                           // 138
    E_9(E, OCTAVE_9),                                                           // 139
    E_10(E, OCTAVE_10),                                                         // 140

    E_SHARP_0(E_SHARP, OCTAVE_0),                                               // 141
    E_SHARP_1(E_SHARP, OCTAVE_1),
    E_SHARP_2(E_SHARP, OCTAVE_2),
    E_SHARP_3(E_SHARP, OCTAVE_3),
    E_SHARP_4(E_SHARP, OCTAVE_4),
    E_SHARP_5(E_SHARP, OCTAVE_5),
    E_SHARP_6(E_SHARP, OCTAVE_6),
    E_SHARP_7(E_SHARP, OCTAVE_7),
    E_SHARP_8(E_SHARP, OCTAVE_8),
    E_SHARP_9(E_SHARP, OCTAVE_9),
    E_SHARP_10(E_SHARP, OCTAVE_10),                                             // 151

    E_DOUBLE_SHARP_0(E_DOUBLE_SHARP, OCTAVE_0),                                 // 152
    E_DOUBLE_SHARP_1(E_DOUBLE_SHARP, OCTAVE_1),
    E_DOUBLE_SHARP_2(E_DOUBLE_SHARP, OCTAVE_2),
    E_DOUBLE_SHARP_3(E_DOUBLE_SHARP, OCTAVE_3),
    E_DOUBLE_SHARP_4(E_DOUBLE_SHARP, OCTAVE_4),
    E_DOUBLE_SHARP_5(E_DOUBLE_SHARP, OCTAVE_5),
    E_DOUBLE_SHARP_6(E_DOUBLE_SHARP, OCTAVE_6),
    E_DOUBLE_SHARP_7(E_DOUBLE_SHARP, OCTAVE_7),
    E_DOUBLE_SHARP_8(E_DOUBLE_SHARP, OCTAVE_8),
    E_DOUBLE_SHARP_9(E_DOUBLE_SHARP, OCTAVE_9),
    E_DOUBLE_SHARP_10(E_DOUBLE_SHARP, OCTAVE_10),                               // 162



    F_DOUBLE_FLAT_0(F_DOUBLE_FLAT, OCTAVE_0),                                   // 163
    F_DOUBLE_FLAT_1(F_DOUBLE_FLAT, OCTAVE_1),
    F_DOUBLE_FLAT_2(F_DOUBLE_FLAT, OCTAVE_2),
    F_DOUBLE_FLAT_3(F_DOUBLE_FLAT, OCTAVE_3),
    F_DOUBLE_FLAT_4(F_DOUBLE_FLAT, OCTAVE_4),
    F_DOUBLE_FLAT_5(F_DOUBLE_FLAT, OCTAVE_5),
    F_DOUBLE_FLAT_6(F_DOUBLE_FLAT, OCTAVE_6),
    F_DOUBLE_FLAT_7(F_DOUBLE_FLAT, OCTAVE_7),
    F_DOUBLE_FLAT_8(F_DOUBLE_FLAT, OCTAVE_8),
    F_DOUBLE_FLAT_9(F_DOUBLE_FLAT, OCTAVE_9),
    F_DOUBLE_FLAT_10(F_DOUBLE_FLAT, OCTAVE_10),                                 // 173

    F_FLAT_0(F_FLAT, OCTAVE_0),                                                 // 174
    F_FLAT_1(F_FLAT, OCTAVE_1),
    F_FLAT_2(F_FLAT, OCTAVE_2),
    F_FLAT_3(F_FLAT, OCTAVE_3),
    F_FLAT_4(F_FLAT, OCTAVE_4),
    F_FLAT_5(F_FLAT, OCTAVE_5),
    F_FLAT_6(F_FLAT, OCTAVE_6),
    F_FLAT_7(F_FLAT, OCTAVE_7),
    F_FLAT_8(F_FLAT, OCTAVE_8),
    F_FLAT_9(F_FLAT, OCTAVE_9),
    F_FLAT_10(F_FLAT, OCTAVE_10),                                               // 184

    F_0(F, OCTAVE_0),                                                           // 185
    F_1(F, OCTAVE_1),
    F_2(F, OCTAVE_2),
    F_3(F, OCTAVE_3),
    F_4(F, OCTAVE_4),
    F_5(F, OCTAVE_5),
    F_6(F, OCTAVE_6),
    F_7(F, OCTAVE_7),
    F_8(F, OCTAVE_8),
    F_9(F, OCTAVE_9),
    F_10(F, OCTAVE_10),                                                         // 195

    F_SHARP_0(F_SHARP, OCTAVE_0),                                               // 196
    F_SHARP_1(F_SHARP, OCTAVE_1),
    F_SHARP_2(F_SHARP, OCTAVE_2),
    F_SHARP_3(F_SHARP, OCTAVE_3),
    F_SHARP_4(F_SHARP, OCTAVE_4),
    F_SHARP_5(F_SHARP, OCTAVE_5),
    F_SHARP_6(F_SHARP, OCTAVE_6),
    F_SHARP_7(F_SHARP, OCTAVE_7),
    F_SHARP_8(F_SHARP, OCTAVE_8),
    F_SHARP_9(F_SHARP, OCTAVE_9),
    F_SHARP_10(F_SHARP, OCTAVE_10),                                             // 206

    // Fx10 is out of playable MIDI range, so it has been removed
    F_DOUBLE_SHARP_0(F_DOUBLE_SHARP, OCTAVE_0),                                 // 207
    F_DOUBLE_SHARP_1(F_DOUBLE_SHARP, OCTAVE_1),
    F_DOUBLE_SHARP_2(F_DOUBLE_SHARP, OCTAVE_2),
    F_DOUBLE_SHARP_3(F_DOUBLE_SHARP, OCTAVE_3),
    F_DOUBLE_SHARP_4(F_DOUBLE_SHARP, OCTAVE_4),
    F_DOUBLE_SHARP_5(F_DOUBLE_SHARP, OCTAVE_5),
    F_DOUBLE_SHARP_6(F_DOUBLE_SHARP, OCTAVE_6),
    F_DOUBLE_SHARP_7(F_DOUBLE_SHARP, OCTAVE_7),
    F_DOUBLE_SHARP_8(F_DOUBLE_SHARP, OCTAVE_8),
    F_DOUBLE_SHARP_9(F_DOUBLE_SHARP, OCTAVE_9),                                 // 216



    G_DOUBLE_FLAT_0(G_DOUBLE_FLAT, OCTAVE_0),                                   // 217
    G_DOUBLE_FLAT_1(G_DOUBLE_FLAT, OCTAVE_1),
    G_DOUBLE_FLAT_2(G_DOUBLE_FLAT, OCTAVE_2),
    G_DOUBLE_FLAT_3(G_DOUBLE_FLAT, OCTAVE_3),
    G_DOUBLE_FLAT_4(G_DOUBLE_FLAT, OCTAVE_4),
    G_DOUBLE_FLAT_5(G_DOUBLE_FLAT, OCTAVE_5),
    G_DOUBLE_FLAT_6(G_DOUBLE_FLAT, OCTAVE_6),
    G_DOUBLE_FLAT_7(G_DOUBLE_FLAT, OCTAVE_7),
    G_DOUBLE_FLAT_8(G_DOUBLE_FLAT, OCTAVE_8),
    G_DOUBLE_FLAT_9(G_DOUBLE_FLAT, OCTAVE_9),
    G_DOUBLE_FLAT_10(G_DOUBLE_FLAT, OCTAVE_10),                                 // 227

    G_FLAT_0(G_FLAT, OCTAVE_0),                                                 // 228
    G_FLAT_1(G_FLAT, OCTAVE_1),
    G_FLAT_2(G_FLAT, OCTAVE_2),
    G_FLAT_3(G_FLAT, OCTAVE_3),
    G_FLAT_4(G_FLAT, OCTAVE_4),
    G_FLAT_5(G_FLAT, OCTAVE_5),
    G_FLAT_6(G_FLAT, OCTAVE_6),
    G_FLAT_7(G_FLAT, OCTAVE_7),
    G_FLAT_8(G_FLAT, OCTAVE_8),
    G_FLAT_9(G_FLAT, OCTAVE_9),
    G_FLAT_10(G_FLAT, OCTAVE_10),                                               // 238

    G_0(G, OCTAVE_0),                                                           // 239
    G_1(G, OCTAVE_1),
    G_2(G, OCTAVE_2),
    G_3(G, OCTAVE_3),
    G_4(G, OCTAVE_4),
    G_5(G, OCTAVE_5),
    G_6(G, OCTAVE_6),
    G_7(G, OCTAVE_7),
    G_8(G, OCTAVE_8),
    G_9(G, OCTAVE_9),
    G_10(G, OCTAVE_10),                                                         // 249

    /*
     *  All Pitches beyond this point that are enharmonically higher than G
     *  are not playable on OCTAVE_10 (because they are out of the MIDI range),
     *  so they must not contain OCTAVE_10.
     *
     *  I.e., the only enums beyond this point that should contain OCTAVE_10
     *  are A_DOUBLE_FLAT_10 (Abb10 == G10), B_SHARP_10 (B#10 == C10),
     *  and B_DOUBLE_SHARP_10 (Bx10 == C#10).
     */

    G_SHARP_0(G_SHARP, OCTAVE_0),                                               // 250
    G_SHARP_1(G_SHARP, OCTAVE_1),
    G_SHARP_2(G_SHARP, OCTAVE_2),
    G_SHARP_3(G_SHARP, OCTAVE_3),
    G_SHARP_4(G_SHARP, OCTAVE_4),
    G_SHARP_5(G_SHARP, OCTAVE_5),
    G_SHARP_6(G_SHARP, OCTAVE_6),
    G_SHARP_7(G_SHARP, OCTAVE_7),
    G_SHARP_8(G_SHARP, OCTAVE_8),
    G_SHARP_9(G_SHARP, OCTAVE_9),                                               // 259

    G_DOUBLE_SHARP_0(G_DOUBLE_SHARP, OCTAVE_0),                                 // 260
    G_DOUBLE_SHARP_1(G_DOUBLE_SHARP, OCTAVE_1),
    G_DOUBLE_SHARP_2(G_DOUBLE_SHARP, OCTAVE_2),
    G_DOUBLE_SHARP_3(G_DOUBLE_SHARP, OCTAVE_3),
    G_DOUBLE_SHARP_4(G_DOUBLE_SHARP, OCTAVE_4),
    G_DOUBLE_SHARP_5(G_DOUBLE_SHARP, OCTAVE_5),
    G_DOUBLE_SHARP_6(G_DOUBLE_SHARP, OCTAVE_6),
    G_DOUBLE_SHARP_7(G_DOUBLE_SHARP, OCTAVE_7),
    G_DOUBLE_SHARP_8(G_DOUBLE_SHARP, OCTAVE_8),
    G_DOUBLE_SHARP_9(G_DOUBLE_SHARP, OCTAVE_9),                                 // 269



    A_DOUBLE_FLAT_0(A_DOUBLE_FLAT, OCTAVE_0),                                   // 270
    A_DOUBLE_FLAT_1(A_DOUBLE_FLAT, OCTAVE_1),
    A_DOUBLE_FLAT_2(A_DOUBLE_FLAT, OCTAVE_2),
    A_DOUBLE_FLAT_3(A_DOUBLE_FLAT, OCTAVE_3),
    A_DOUBLE_FLAT_4(A_DOUBLE_FLAT, OCTAVE_4),
    A_DOUBLE_FLAT_5(A_DOUBLE_FLAT, OCTAVE_5),
    A_DOUBLE_FLAT_6(A_DOUBLE_FLAT, OCTAVE_6),
    A_DOUBLE_FLAT_7(A_DOUBLE_FLAT, OCTAVE_7),
    A_DOUBLE_FLAT_8(A_DOUBLE_FLAT, OCTAVE_8),
    A_DOUBLE_FLAT_9(A_DOUBLE_FLAT, OCTAVE_9),
    A_DOUBLE_FLAT_10(A_DOUBLE_FLAT, OCTAVE_10),                                 // 280

    A_FLAT_0(A_FLAT, OCTAVE_0),                                                 // 281
    A_FLAT_1(A_FLAT, OCTAVE_1),
    A_FLAT_2(A_FLAT, OCTAVE_2),
    A_FLAT_3(A_FLAT, OCTAVE_3),
    A_FLAT_4(A_FLAT, OCTAVE_4),
    A_FLAT_5(A_FLAT, OCTAVE_5),
    A_FLAT_6(A_FLAT, OCTAVE_6),
    A_FLAT_7(A_FLAT, OCTAVE_7),
    A_FLAT_8(A_FLAT, OCTAVE_8),
    A_FLAT_9(A_FLAT, OCTAVE_9),                                                 // 290

    A_0(A, OCTAVE_0),                                                           // 291
    A_1(A, OCTAVE_1),
    A_2(A, OCTAVE_2),
    A_3(A, OCTAVE_3),
    A_4(A, OCTAVE_4),
    A_5(A, OCTAVE_5),
    A_6(A, OCTAVE_6),
    A_7(A, OCTAVE_7),
    A_8(A, OCTAVE_8),
    A_9(A, OCTAVE_9),                                                           // 300

    A_SHARP_0(A_SHARP, OCTAVE_0),                                               // 301
    A_SHARP_1(A_SHARP, OCTAVE_1),
    A_SHARP_2(A_SHARP, OCTAVE_2),
    A_SHARP_3(A_SHARP, OCTAVE_3),
    A_SHARP_4(A_SHARP, OCTAVE_4),
    A_SHARP_5(A_SHARP, OCTAVE_5),
    A_SHARP_6(A_SHARP, OCTAVE_6),
    A_SHARP_7(A_SHARP, OCTAVE_7),
    A_SHARP_8(A_SHARP, OCTAVE_8),
    A_SHARP_9(A_SHARP, OCTAVE_9),                                               // 310

    A_DOUBLE_SHARP_0(A_DOUBLE_SHARP, OCTAVE_0),                                 // 311
    A_DOUBLE_SHARP_1(A_DOUBLE_SHARP, OCTAVE_1),
    A_DOUBLE_SHARP_2(A_DOUBLE_SHARP, OCTAVE_2),
    A_DOUBLE_SHARP_3(A_DOUBLE_SHARP, OCTAVE_3),
    A_DOUBLE_SHARP_4(A_DOUBLE_SHARP, OCTAVE_4),
    A_DOUBLE_SHARP_5(A_DOUBLE_SHARP, OCTAVE_5),
    A_DOUBLE_SHARP_6(A_DOUBLE_SHARP, OCTAVE_6),
    A_DOUBLE_SHARP_7(A_DOUBLE_SHARP, OCTAVE_7),
    A_DOUBLE_SHARP_8(A_DOUBLE_SHARP, OCTAVE_8),
    A_DOUBLE_SHARP_9(A_DOUBLE_SHARP, OCTAVE_9),                                 // 320



    B_DOUBLE_FLAT_0(B_DOUBLE_FLAT, OCTAVE_0),                                   // 321
    B_DOUBLE_FLAT_1(B_DOUBLE_FLAT, OCTAVE_1),
    B_DOUBLE_FLAT_2(B_DOUBLE_FLAT, OCTAVE_2),
    B_DOUBLE_FLAT_3(B_DOUBLE_FLAT, OCTAVE_3),
    B_DOUBLE_FLAT_4(B_DOUBLE_FLAT, OCTAVE_4),
    B_DOUBLE_FLAT_5(B_DOUBLE_FLAT, OCTAVE_5),
    B_DOUBLE_FLAT_6(B_DOUBLE_FLAT, OCTAVE_6),
    B_DOUBLE_FLAT_7(B_DOUBLE_FLAT, OCTAVE_7),
    B_DOUBLE_FLAT_8(B_DOUBLE_FLAT, OCTAVE_8),
    B_DOUBLE_FLAT_9(B_DOUBLE_FLAT, OCTAVE_9),                                   // 330

    B_FLAT_0(B_FLAT, OCTAVE_0),                                                 // 331
    B_FLAT_1(B_FLAT, OCTAVE_1),
    B_FLAT_2(B_FLAT, OCTAVE_2),
    B_FLAT_3(B_FLAT, OCTAVE_3),
    B_FLAT_4(B_FLAT, OCTAVE_4),
    B_FLAT_5(B_FLAT, OCTAVE_5),
    B_FLAT_6(B_FLAT, OCTAVE_6),
    B_FLAT_7(B_FLAT, OCTAVE_7),
    B_FLAT_8(B_FLAT, OCTAVE_8),
    B_FLAT_9(B_FLAT, OCTAVE_9),                                                 // 340

    B_0(B, OCTAVE_0),                                                           // 341
    B_1(B, OCTAVE_1),
    B_2(B, OCTAVE_2),
    B_3(B, OCTAVE_3),
    B_4(B, OCTAVE_4),
    B_5(B, OCTAVE_5),
    B_6(B, OCTAVE_6),
    B_7(B, OCTAVE_7),
    B_8(B, OCTAVE_8),
    B_9(B, OCTAVE_9),                                                           // 350

    B_SHARP_0(B_SHARP, OCTAVE_0),                                               // 351
    B_SHARP_1(B_SHARP, OCTAVE_1),
    B_SHARP_2(B_SHARP, OCTAVE_2),
    B_SHARP_3(B_SHARP, OCTAVE_3),
    B_SHARP_4(B_SHARP, OCTAVE_4),
    B_SHARP_5(B_SHARP, OCTAVE_5),
    B_SHARP_6(B_SHARP, OCTAVE_6),
    B_SHARP_7(B_SHARP, OCTAVE_7),
    B_SHARP_8(B_SHARP, OCTAVE_8),
    B_SHARP_9(B_SHARP, OCTAVE_9),
    B_SHARP_10(B_SHARP, OCTAVE_10),                                             // 360

    B_DOUBLE_SHARP_0(B_DOUBLE_SHARP, OCTAVE_0),                                 // 361
    B_DOUBLE_SHARP_1(B_DOUBLE_SHARP, OCTAVE_1),
    B_DOUBLE_SHARP_2(B_DOUBLE_SHARP, OCTAVE_2),
    B_DOUBLE_SHARP_3(B_DOUBLE_SHARP, OCTAVE_3),
    B_DOUBLE_SHARP_4(B_DOUBLE_SHARP, OCTAVE_4),
    B_DOUBLE_SHARP_5(B_DOUBLE_SHARP, OCTAVE_5),
    B_DOUBLE_SHARP_6(B_DOUBLE_SHARP, OCTAVE_6),
    B_DOUBLE_SHARP_7(B_DOUBLE_SHARP, OCTAVE_7),
    B_DOUBLE_SHARP_8(B_DOUBLE_SHARP, OCTAVE_8),
    B_DOUBLE_SHARP_9(B_DOUBLE_SHARP, OCTAVE_9),
    B_DOUBLE_SHARP_10(B_DOUBLE_SHARP, OCTAVE_10),                               // 371
    ;

    private final PitchClass PITCH_CLASS;
    private final Octave OCTAVE;
    private final int ABSOLUTE_PITCH;

    private static final Pitch[] VALUES = Pitch.values();
    private static final Map<Integer, ArrayList<Pitch>> ENHARMONICS = new HashMap<>(127);
        static {
            for (int i = 0; i <= 127; i++) {
                ENHARMONICS.put(i, new ArrayList<>());
            }

            for (Pitch pitch : VALUES) {
                ENHARMONICS.get(pitch.getAbsolutePitch()).add(pitch);
            }
        }

    /*
     * This array is used exclusively for the purpose of
     * grouping all enharmonic PitchClasses, which simplifies
     * the algorithm in transposeTo(PitchInterval).
     */
    private static final PitchClass[][] PITCH_CLASS_CANDIDATE_REFERENCE =
            {
                    C.getEnharmonics(),
                    C_SHARP.getEnharmonics(),
                    D.getEnharmonics(),
                    D_SHARP.getEnharmonics(),
                    E.getEnharmonics(),
                    F.getEnharmonics(),
                    F_SHARP.getEnharmonics(),
                    G.getEnharmonics(),
                    G_SHARP.getEnharmonics(),
                    A.getEnharmonics(),
                    A_SHARP.getEnharmonics(),
                    B.getEnharmonics(),
            };

    Pitch(PitchClass pitchClass, Octave octave) {
        this.PITCH_CLASS = pitchClass;
        this.OCTAVE = octave;
        this.ABSOLUTE_PITCH = octave.getMidiStart() + pitchClass.getBaseMidiValue();
    }

    public PitchClass getPitchClass() {
        return PITCH_CLASS;
    }

    public Octave getOctave() {
        return OCTAVE;
    }

    public int getAbsolutePitch() {
        return ABSOLUTE_PITCH;
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return this.PITCH_CLASS.isDiatonicTo(keySignature);
    }

    @Override
    public boolean isEnharmonicTo(@NotNull Pitch other) {
        return this.PITCH_CLASS.isEnharmonicTo(other.PITCH_CLASS);
    }

    @Override
    public Pitch[] getEnharmonics() {
        Pitch[] enharmonics = new Pitch[ENHARMONICS.get(ABSOLUTE_PITCH).size()];
        return ENHARMONICS.get(ABSOLUTE_PITCH).toArray(enharmonics);
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchInterval pitchInterval, @NotNull boolean direction) {
        return direction
                ? ABSOLUTE_PITCH + pitchInterval.getNumSemitones() <= 127
                : ABSOLUTE_PITCH - pitchInterval.getNumSemitones() >= 0;
    }

    @NotNull
    @Override
    public Pitch transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        if (isTransposableTo(pitchInterval, direction)) {

            // The returned Pitch will contain this PitchClass
            PitchClass transposedPitchClass = null;

            // 1. Get the group of enharmonic PitchClasses. This is our candidate set.
            int idx = PITCH_CLASS.getBaseMidiValue();
            int enharmonicIndex = direction
                    ? (idx + pitchInterval.getNumSemitones()) % 12
                    : (idx + 12 - pitchInterval.getNumSemitones()) % 12;
            PitchClass[] candidates = PITCH_CLASS_CANDIDATE_REFERENCE[enharmonicIndex];

            // 2. Determine the expected letter of the returned Pitch. It may or may not exist in the candidate set.
            idx = PITCH_CLASS.getEnharmonicSpelling().getLetter().ordinal();
            int expectedLetterIndex = direction
                    ? (idx + (pitchInterval.getNumber() - 1)) % 7
                    : (idx + (7 - (pitchInterval.getNumber() - 1))) % 7;
            Letter expectedLetter = Letter.values()[expectedLetterIndex];

            // 3. Choose a PitchClass from the candidate set.
            int shortestDistance = 7, indexWithShortestDistance = -1;
            for (int i = 0; i < candidates.length; i++) {
                if (candidates[i].getEnharmonicSpelling().getLetter().equals(expectedLetter)) {
                    /*
                     * This candidate's letter matches the expected letter.
                     * It can be verified that no two candidates in the set possess the same letter,
                     * so we know this is the correct PitchClass to use.
                     */
                    transposedPitchClass = candidates[i];
                    break;
                }

                /*
                 * Keep track of the index with the shortest distance to the expected letter.
                 * We're doing this in case we never find the expected letter among the candidates.
                 */
                int candidateLetterIndex = candidates[i].getEnharmonicSpelling().getLetter().ordinal();
                int
                        distanceRight = (7 - (expectedLetterIndex - candidateLetterIndex)) % 7,
                        distanceLeft =  (7 - (candidateLetterIndex - expectedLetterIndex)) % 7,
                        distance = Math.min(distanceLeft, distanceRight);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    indexWithShortestDistance = i;
                }
            }
            if (transposedPitchClass == null) {
                /*
                 * This condition ONLY occurs if the for-loop above
                 * never found the expected letter among the candidates.
                 */
                transposedPitchClass = candidates[indexWithShortestDistance];
            }

            // Substitute the non-natural equivalent for transposed PitchClasses with natural Accidentals.
            String name = transposedPitchClass.name();
            if (name.contains("_NATURAL")) {
                name = name.substring(0, name.indexOf("_NATURAL"));
            }

            // Decide which Octave to use for the returned Pitch.
            Pitch candidate = Pitch.valueOf(name + "_" + OCTAVE.getOctaveNumber());
            if (candidate.PITCH_CLASS.equals(PITCH_CLASS) || candidate.ABSOLUTE_PITCH == ABSOLUTE_PITCH) {
                return VALUES[direction ? candidate.ordinal() + 1 : candidate.ordinal() - 1];
            }
            else if (PITCH_CLASS.getBaseMidiValue() < transposedPitchClass.getBaseMidiValue()) {
                return direction ? candidate : VALUES[candidate.ordinal() - 1];
            }
            else if (PITCH_CLASS.getBaseMidiValue() > transposedPitchClass.getBaseMidiValue()) {
                return direction ? VALUES[candidate.ordinal() + 1] : candidate;
            }
            else return candidate;
        }

        return null; // Returned on error
    }

    @Override
    public boolean isTransposableTo(@NotNull Octave octave) {
        return PITCH_CLASS.getOctaveRange().getMidiStart() >= octave.getMidiStart();
    }

    @NotNull
    @Override
    public Pitch transposeTo(@NotNull Octave octave) {
        return isTransposableTo(octave)
                ? Pitch.valueOf(PITCH_CLASS + "_" + octave.getOctaveNumber())
                : null;
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        try {
            Pitch candidate = Pitch.valueOf(pitchClass.name() + "_" + octave.getOctaveNumber());

            /*
             * Reaching this return statement without throwing an Exception
             * guarantees that the current Pitch is transposable to the candidate.
             */
            return true;
        }
        catch (IllegalArgumentException ex) {
            /*
             * Pitch.valueOf() failed to find the desired Pitch enum value.
             * Therefore, the current Pitch is not transposable to the candidate.
             */
            return false;
        }
        catch (NullPointerException ex) {
            /*
             * One or both of this function's arguments were passed as null,
             * which cannot be handled by Pitch.valueOf().
             * It is not possible to transpose to null objects.
             */
            return false;
        }
    }

    @NotNull
    @Override
    public Pitch transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return isTransposableTo(pitchClass, octave)
                ? Pitch.valueOf(pitchClass.name() + "_" + octave.getOctaveNumber())
                : null;
    }

    @Override
    public boolean isTransposableTo(@NotNull Pitch pitch) {
        /*
         * Since Pitches are inherently bounded by this enumerated class,
         * they can all be transposed to the other Pitch.
         */
        return true;
    }

    @NotNull
    @Override
    public Pitch transposeTo(@NotNull Pitch pitch) {
        return pitch;
    }
}
