package chordinnate.musictheory.pitch;

import chordinnate.musictheory.pitch.notation.Accidental;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.interval.PitchInterval;
import chordinnate.musictheory.pitch.interval.set.IntervalSet;
import chordinnate.musictheory.pitch.notation.KeySignature;
import chordinnate.musictheory.pitch.notation.Letter;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 4/18/16.
 */
public enum Pitch
        implements TransposableEnum<Pitch>, Enharmonic<Pitch>, Diatonic {

    // Cbb10 is out of playable MIDI range, so it has been removed
    C_DOUBLE_FLAT_0(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 0
    C_DOUBLE_FLAT_1(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 1
    C_DOUBLE_FLAT_2(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 2
    C_DOUBLE_FLAT_3(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 3
    C_DOUBLE_FLAT_4(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 4
    C_DOUBLE_FLAT_5(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 5
    C_DOUBLE_FLAT_6(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 6
    C_DOUBLE_FLAT_7(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 7
    C_DOUBLE_FLAT_8(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 8
    C_DOUBLE_FLAT_9(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 9

    // Cb10 is out of playable MIDI range, so it has been removed
    C_FLAT_0(PitchClass.C_FLAT, Octave.OCTAVE_0),                                                 // 10
    C_FLAT_1(PitchClass.C_FLAT, Octave.OCTAVE_1),                                                 // 11
    C_FLAT_2(PitchClass.C_FLAT, Octave.OCTAVE_2),                                                 // 12
    C_FLAT_3(PitchClass.C_FLAT, Octave.OCTAVE_3),                                                 // 13
    C_FLAT_4(PitchClass.C_FLAT, Octave.OCTAVE_4),                                                 // 14
    C_FLAT_5(PitchClass.C_FLAT, Octave.OCTAVE_5),                                                 // 15
    C_FLAT_6(PitchClass.C_FLAT, Octave.OCTAVE_6),                                                 // 16
    C_FLAT_7(PitchClass.C_FLAT, Octave.OCTAVE_7),                                                 // 17
    C_FLAT_8(PitchClass.C_FLAT, Octave.OCTAVE_8),                                                 // 18
    C_FLAT_9(PitchClass.C_FLAT, Octave.OCTAVE_9),                                                 // 19

    C_0(PitchClass.C, Octave.OCTAVE_0),                                                           // 20
    C_1(PitchClass.C, Octave.OCTAVE_1),                                                           // 21
    C_2(PitchClass.C, Octave.OCTAVE_2),                                                           // 22
    C_3(PitchClass.C, Octave.OCTAVE_3),                                                           // 23
    C_4(PitchClass.C, Octave.OCTAVE_4),                                                           // 24
    C_5(PitchClass.C, Octave.OCTAVE_5),                                                           // 25
    C_6(PitchClass.C, Octave.OCTAVE_6),                                                           // 26
    C_7(PitchClass.C, Octave.OCTAVE_7),                                                           // 27
    C_8(PitchClass.C, Octave.OCTAVE_8),                                                           // 28
    C_9(PitchClass.C, Octave.OCTAVE_9),                                                           // 29
    C_10(PitchClass.C, Octave.OCTAVE_10),                                                         // 30

    C_SHARP_0(PitchClass.C_SHARP, Octave.OCTAVE_0),                                               // 31
    C_SHARP_1(PitchClass.C_SHARP, Octave.OCTAVE_1),                                               // 32
    C_SHARP_2(PitchClass.C_SHARP, Octave.OCTAVE_2),                                               // 33
    C_SHARP_3(PitchClass.C_SHARP, Octave.OCTAVE_3),                                               // 34
    C_SHARP_4(PitchClass.C_SHARP, Octave.OCTAVE_4),                                               // 35
    C_SHARP_5(PitchClass.C_SHARP, Octave.OCTAVE_5),                                               // 36
    C_SHARP_6(PitchClass.C_SHARP, Octave.OCTAVE_6),                                               // 37
    C_SHARP_7(PitchClass.C_SHARP, Octave.OCTAVE_7),                                               // 38
    C_SHARP_8(PitchClass.C_SHARP, Octave.OCTAVE_8),                                               // 39
    C_SHARP_9(PitchClass.C_SHARP, Octave.OCTAVE_9),                                               // 40
    C_SHARP_10(PitchClass.C_SHARP, Octave.OCTAVE_10),                                             // 41

    C_DOUBLE_SHARP_0(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 42
    C_DOUBLE_SHARP_1(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 43
    C_DOUBLE_SHARP_2(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 44
    C_DOUBLE_SHARP_3(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 45
    C_DOUBLE_SHARP_4(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 46
    C_DOUBLE_SHARP_5(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 47
    C_DOUBLE_SHARP_6(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 48
    C_DOUBLE_SHARP_7(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 49
    C_DOUBLE_SHARP_8(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 50
    C_DOUBLE_SHARP_9(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 51
    C_DOUBLE_SHARP_10(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_10),                               // 52



    D_DOUBLE_FLAT_0(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 53
    D_DOUBLE_FLAT_1(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 54
    D_DOUBLE_FLAT_2(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 55
    D_DOUBLE_FLAT_3(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 56
    D_DOUBLE_FLAT_4(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 57
    D_DOUBLE_FLAT_5(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 58
    D_DOUBLE_FLAT_6(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 59
    D_DOUBLE_FLAT_7(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 60
    D_DOUBLE_FLAT_8(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 61
    D_DOUBLE_FLAT_9(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 62
    D_DOUBLE_FLAT_10(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_10),                                 // 63

    D_FLAT_0(PitchClass.D_FLAT, Octave.OCTAVE_0),                                                 // 64
    D_FLAT_1(PitchClass.D_FLAT, Octave.OCTAVE_1),                                                 // 65
    D_FLAT_2(PitchClass.D_FLAT, Octave.OCTAVE_2),                                                 // 66
    D_FLAT_3(PitchClass.D_FLAT, Octave.OCTAVE_3),                                                 // 67
    D_FLAT_4(PitchClass.D_FLAT, Octave.OCTAVE_4),                                                 // 68
    D_FLAT_5(PitchClass.D_FLAT, Octave.OCTAVE_5),                                                 // 69
    D_FLAT_6(PitchClass.D_FLAT, Octave.OCTAVE_6),                                                 // 70
    D_FLAT_7(PitchClass.D_FLAT, Octave.OCTAVE_7),                                                 // 71
    D_FLAT_8(PitchClass.D_FLAT, Octave.OCTAVE_8),                                                 // 72
    D_FLAT_9(PitchClass.D_FLAT, Octave.OCTAVE_9),                                                 // 73
    D_FLAT_10(PitchClass.D_FLAT, Octave.OCTAVE_10),                                               // 74

    D_0(PitchClass.D, Octave.OCTAVE_0),                                                           // 75
    D_1(PitchClass.D, Octave.OCTAVE_1),                                                           // 76
    D_2(PitchClass.D, Octave.OCTAVE_2),                                                           // 77
    D_3(PitchClass.D, Octave.OCTAVE_3),                                                           // 78
    D_4(PitchClass.D, Octave.OCTAVE_4),                                                           // 79
    D_5(PitchClass.D, Octave.OCTAVE_5),                                                           // 80
    D_6(PitchClass.D, Octave.OCTAVE_6),                                                           // 81
    D_7(PitchClass.D, Octave.OCTAVE_7),                                                           // 82
    D_8(PitchClass.D, Octave.OCTAVE_8),                                                           // 83
    D_9(PitchClass.D, Octave.OCTAVE_9),                                                           // 84
    D_10(PitchClass.D, Octave.OCTAVE_10),                                                         // 85

    D_SHARP_0(PitchClass.D_SHARP, Octave.OCTAVE_0),                                               // 86
    D_SHARP_1(PitchClass.D_SHARP, Octave.OCTAVE_1),                                               // 87
    D_SHARP_2(PitchClass.D_SHARP, Octave.OCTAVE_2),                                               // 88
    D_SHARP_3(PitchClass.D_SHARP, Octave.OCTAVE_3),                                               // 89
    D_SHARP_4(PitchClass.D_SHARP, Octave.OCTAVE_4),                                               // 90
    D_SHARP_5(PitchClass.D_SHARP, Octave.OCTAVE_5),                                               // 91
    D_SHARP_6(PitchClass.D_SHARP, Octave.OCTAVE_6),                                               // 92
    D_SHARP_7(PitchClass.D_SHARP, Octave.OCTAVE_7),                                               // 93
    D_SHARP_8(PitchClass.D_SHARP, Octave.OCTAVE_8),                                               // 94
    D_SHARP_9(PitchClass.D_SHARP, Octave.OCTAVE_9),                                               // 95
    D_SHARP_10(PitchClass.D_SHARP, Octave.OCTAVE_10),                                             // 96

    D_DOUBLE_SHARP_0(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 97
    D_DOUBLE_SHARP_1(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 98
    D_DOUBLE_SHARP_2(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 99
    D_DOUBLE_SHARP_3(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 100
    D_DOUBLE_SHARP_4(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 101
    D_DOUBLE_SHARP_5(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 102
    D_DOUBLE_SHARP_6(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 103
    D_DOUBLE_SHARP_7(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 104
    D_DOUBLE_SHARP_8(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 105
    D_DOUBLE_SHARP_9(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 106
    D_DOUBLE_SHARP_10(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_10),                               // 107
    
    
    

    E_DOUBLE_FLAT_0(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 108
    E_DOUBLE_FLAT_1(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 109
    E_DOUBLE_FLAT_2(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 110
    E_DOUBLE_FLAT_3(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 111
    E_DOUBLE_FLAT_4(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 112
    E_DOUBLE_FLAT_5(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 113
    E_DOUBLE_FLAT_6(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 114
    E_DOUBLE_FLAT_7(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 115
    E_DOUBLE_FLAT_8(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 116
    E_DOUBLE_FLAT_9(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 117
    E_DOUBLE_FLAT_10(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_10),                                 // 118

    E_FLAT_0(PitchClass.E_FLAT, Octave.OCTAVE_0),                                                 // 119
    E_FLAT_1(PitchClass.E_FLAT, Octave.OCTAVE_1),                                                 // 120
    E_FLAT_2(PitchClass.E_FLAT, Octave.OCTAVE_2),                                                 // 121
    E_FLAT_3(PitchClass.E_FLAT, Octave.OCTAVE_3),                                                 // 122
    E_FLAT_4(PitchClass.E_FLAT, Octave.OCTAVE_4),                                                 // 123
    E_FLAT_5(PitchClass.E_FLAT, Octave.OCTAVE_5),                                                 // 124
    E_FLAT_6(PitchClass.E_FLAT, Octave.OCTAVE_6),                                                 // 125
    E_FLAT_7(PitchClass.E_FLAT, Octave.OCTAVE_7),                                                 // 126
    E_FLAT_8(PitchClass.E_FLAT, Octave.OCTAVE_8),                                                 // 127
    E_FLAT_9(PitchClass.E_FLAT, Octave.OCTAVE_9),                                                 // 128
    E_FLAT_10(PitchClass.E_FLAT, Octave.OCTAVE_10),                                               // 129

    E_0(PitchClass.E, Octave.OCTAVE_0),                                                           // 130
    E_1(PitchClass.E, Octave.OCTAVE_1),                                                           // 131
    E_2(PitchClass.E, Octave.OCTAVE_2),                                                           // 132
    E_3(PitchClass.E, Octave.OCTAVE_3),                                                           // 133
    E_4(PitchClass.E, Octave.OCTAVE_4),                                                           // 134
    E_5(PitchClass.E, Octave.OCTAVE_5),                                                           // 135
    E_6(PitchClass.E, Octave.OCTAVE_6),                                                           // 136
    E_7(PitchClass.E, Octave.OCTAVE_7),                                                           // 137
    E_8(PitchClass.E, Octave.OCTAVE_8),                                                           // 138
    E_9(PitchClass.E, Octave.OCTAVE_9),                                                           // 139
    E_10(PitchClass.E, Octave.OCTAVE_10),                                                         // 140

    E_SHARP_0(PitchClass.E_SHARP, Octave.OCTAVE_0),                                               // 141
    E_SHARP_1(PitchClass.E_SHARP, Octave.OCTAVE_1),                                               // 142
    E_SHARP_2(PitchClass.E_SHARP, Octave.OCTAVE_2),                                               // 143
    E_SHARP_3(PitchClass.E_SHARP, Octave.OCTAVE_3),                                               // 144
    E_SHARP_4(PitchClass.E_SHARP, Octave.OCTAVE_4),                                               // 145
    E_SHARP_5(PitchClass.E_SHARP, Octave.OCTAVE_5),                                               // 146
    E_SHARP_6(PitchClass.E_SHARP, Octave.OCTAVE_6),                                               // 147
    E_SHARP_7(PitchClass.E_SHARP, Octave.OCTAVE_7),                                               // 148
    E_SHARP_8(PitchClass.E_SHARP, Octave.OCTAVE_8),                                               // 149
    E_SHARP_9(PitchClass.E_SHARP, Octave.OCTAVE_9),                                               // 150
    E_SHARP_10(PitchClass.E_SHARP, Octave.OCTAVE_10),                                             // 151

    E_DOUBLE_SHARP_0(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 152
    E_DOUBLE_SHARP_1(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 153
    E_DOUBLE_SHARP_2(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 154
    E_DOUBLE_SHARP_3(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 155
    E_DOUBLE_SHARP_4(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 156
    E_DOUBLE_SHARP_5(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 157
    E_DOUBLE_SHARP_6(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 158
    E_DOUBLE_SHARP_7(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 159
    E_DOUBLE_SHARP_8(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 160
    E_DOUBLE_SHARP_9(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 161
    E_DOUBLE_SHARP_10(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_10),                               // 162



    F_DOUBLE_FLAT_0(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 163
    F_DOUBLE_FLAT_1(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 164
    F_DOUBLE_FLAT_2(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 165
    F_DOUBLE_FLAT_3(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 166
    F_DOUBLE_FLAT_4(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 167
    F_DOUBLE_FLAT_5(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 168
    F_DOUBLE_FLAT_6(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 169
    F_DOUBLE_FLAT_7(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 170
    F_DOUBLE_FLAT_8(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 171
    F_DOUBLE_FLAT_9(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 172
    F_DOUBLE_FLAT_10(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_10),                                 // 173

    F_FLAT_0(PitchClass.F_FLAT, Octave.OCTAVE_0),                                                 // 174
    F_FLAT_1(PitchClass.F_FLAT, Octave.OCTAVE_1),                                                 // 175
    F_FLAT_2(PitchClass.F_FLAT, Octave.OCTAVE_2),                                                 // 176
    F_FLAT_3(PitchClass.F_FLAT, Octave.OCTAVE_3),                                                 // 177
    F_FLAT_4(PitchClass.F_FLAT, Octave.OCTAVE_4),                                                 // 178
    F_FLAT_5(PitchClass.F_FLAT, Octave.OCTAVE_5),                                                 // 179
    F_FLAT_6(PitchClass.F_FLAT, Octave.OCTAVE_6),                                                 // 180
    F_FLAT_7(PitchClass.F_FLAT, Octave.OCTAVE_7),                                                 // 181
    F_FLAT_8(PitchClass.F_FLAT, Octave.OCTAVE_8),                                                 // 182
    F_FLAT_9(PitchClass.F_FLAT, Octave.OCTAVE_9),                                                 // 183
    F_FLAT_10(PitchClass.F_FLAT, Octave.OCTAVE_10),                                               // 184

    F_0(PitchClass.F, Octave.OCTAVE_0),                                                           // 185
    F_1(PitchClass.F, Octave.OCTAVE_1),                                                           // 186
    F_2(PitchClass.F, Octave.OCTAVE_2),                                                           // 187
    F_3(PitchClass.F, Octave.OCTAVE_3),                                                           // 188
    F_4(PitchClass.F, Octave.OCTAVE_4),                                                           // 189
    F_5(PitchClass.F, Octave.OCTAVE_5),                                                           // 190
    F_6(PitchClass.F, Octave.OCTAVE_6),                                                           // 191
    F_7(PitchClass.F, Octave.OCTAVE_7),                                                           // 192
    F_8(PitchClass.F, Octave.OCTAVE_8),                                                           // 193
    F_9(PitchClass.F, Octave.OCTAVE_9),                                                           // 194
    F_10(PitchClass.F, Octave.OCTAVE_10),                                                         // 195

    F_SHARP_0(PitchClass.F_SHARP, Octave.OCTAVE_0),                                               // 196
    F_SHARP_1(PitchClass.F_SHARP, Octave.OCTAVE_1),                                               // 197
    F_SHARP_2(PitchClass.F_SHARP, Octave.OCTAVE_2),                                               // 198
    F_SHARP_3(PitchClass.F_SHARP, Octave.OCTAVE_3),                                               // 199
    F_SHARP_4(PitchClass.F_SHARP, Octave.OCTAVE_4),                                               // 200
    F_SHARP_5(PitchClass.F_SHARP, Octave.OCTAVE_5),                                               // 201
    F_SHARP_6(PitchClass.F_SHARP, Octave.OCTAVE_6),                                               // 202
    F_SHARP_7(PitchClass.F_SHARP, Octave.OCTAVE_7),                                               // 203
    F_SHARP_8(PitchClass.F_SHARP, Octave.OCTAVE_8),                                               // 204
    F_SHARP_9(PitchClass.F_SHARP, Octave.OCTAVE_9),                                               // 205
    F_SHARP_10(PitchClass.F_SHARP, Octave.OCTAVE_10),                                             // 206

    F_DOUBLE_SHARP_0(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 207
    F_DOUBLE_SHARP_1(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 208
    F_DOUBLE_SHARP_2(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 209
    F_DOUBLE_SHARP_3(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 210
    F_DOUBLE_SHARP_4(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 211
    F_DOUBLE_SHARP_5(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 212
    F_DOUBLE_SHARP_6(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 213
    F_DOUBLE_SHARP_7(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 214
    F_DOUBLE_SHARP_8(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 215
    F_DOUBLE_SHARP_9(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 216
    F_DOUBLE_SHARP_10(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_10),                               // 217



    G_DOUBLE_FLAT_0(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 218
    G_DOUBLE_FLAT_1(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 219
    G_DOUBLE_FLAT_2(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 220
    G_DOUBLE_FLAT_3(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 221
    G_DOUBLE_FLAT_4(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 222
    G_DOUBLE_FLAT_5(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 223
    G_DOUBLE_FLAT_6(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 224
    G_DOUBLE_FLAT_7(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 225
    G_DOUBLE_FLAT_8(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 226
    G_DOUBLE_FLAT_9(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 227
    G_DOUBLE_FLAT_10(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_10),                                 // 228

    G_FLAT_0(PitchClass.G_FLAT, Octave.OCTAVE_0),                                                 // 229
    G_FLAT_1(PitchClass.G_FLAT, Octave.OCTAVE_1),                                                 // 230
    G_FLAT_2(PitchClass.G_FLAT, Octave.OCTAVE_2),                                                 // 231
    G_FLAT_3(PitchClass.G_FLAT, Octave.OCTAVE_3),                                                 // 232
    G_FLAT_4(PitchClass.G_FLAT, Octave.OCTAVE_4),                                                 // 233
    G_FLAT_5(PitchClass.G_FLAT, Octave.OCTAVE_5),                                                 // 234
    G_FLAT_6(PitchClass.G_FLAT, Octave.OCTAVE_6),                                                 // 235
    G_FLAT_7(PitchClass.G_FLAT, Octave.OCTAVE_7),                                                 // 236
    G_FLAT_8(PitchClass.G_FLAT, Octave.OCTAVE_8),                                                 // 237
    G_FLAT_9(PitchClass.G_FLAT, Octave.OCTAVE_9),                                                 // 238
    G_FLAT_10(PitchClass.G_FLAT, Octave.OCTAVE_10),                                               // 239

    G_0(PitchClass.G, Octave.OCTAVE_0),                                                           // 240
    G_1(PitchClass.G, Octave.OCTAVE_1),                                                           // 241
    G_2(PitchClass.G, Octave.OCTAVE_2),                                                           // 242
    G_3(PitchClass.G, Octave.OCTAVE_3),                                                           // 243
    G_4(PitchClass.G, Octave.OCTAVE_4),                                                           // 244
    G_5(PitchClass.G, Octave.OCTAVE_5),                                                           // 245
    G_6(PitchClass.G, Octave.OCTAVE_6),                                                           // 246
    G_7(PitchClass.G, Octave.OCTAVE_7),                                                           // 247
    G_8(PitchClass.G, Octave.OCTAVE_8),                                                           // 248
    G_9(PitchClass.G, Octave.OCTAVE_9),                                                           // 249
    G_10(PitchClass.G, Octave.OCTAVE_10),                                                         // 250

    /*
     *  All Pitches beyond this point that are enharmonically higher than G
     *  are not playable on OCTAVE_10 (because they are out of the MIDI range),
     *  so they must not contain OCTAVE_10.
     *
     *  I.e., the only enums beyond this point that should contain OCTAVE_10
     *  are A_DOUBLE_FLAT_10 (Abb10 == G10), B_SHARP_10 (B#10 == C10),
     *  and B_DOUBLE_SHARP_10 (Bx10 == C#10).
     */

    G_SHARP_0(PitchClass.G_SHARP, Octave.OCTAVE_0),                                               // 251
    G_SHARP_1(PitchClass.G_SHARP, Octave.OCTAVE_1),                                               // 252
    G_SHARP_2(PitchClass.G_SHARP, Octave.OCTAVE_2),                                               // 253
    G_SHARP_3(PitchClass.G_SHARP, Octave.OCTAVE_3),                                               // 254
    G_SHARP_4(PitchClass.G_SHARP, Octave.OCTAVE_4),                                               // 255
    G_SHARP_5(PitchClass.G_SHARP, Octave.OCTAVE_5),                                               // 256
    G_SHARP_6(PitchClass.G_SHARP, Octave.OCTAVE_6),                                               // 257
    G_SHARP_7(PitchClass.G_SHARP, Octave.OCTAVE_7),                                               // 258
    G_SHARP_8(PitchClass.G_SHARP, Octave.OCTAVE_8),                                               // 259
    G_SHARP_9(PitchClass.G_SHARP, Octave.OCTAVE_9),                                               // 260

    G_DOUBLE_SHARP_0(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 261
    G_DOUBLE_SHARP_1(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 262
    G_DOUBLE_SHARP_2(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 263
    G_DOUBLE_SHARP_3(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 264
    G_DOUBLE_SHARP_4(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 265
    G_DOUBLE_SHARP_5(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 266
    G_DOUBLE_SHARP_6(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 267
    G_DOUBLE_SHARP_7(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 268
    G_DOUBLE_SHARP_8(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 269
    G_DOUBLE_SHARP_9(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 270



    A_DOUBLE_FLAT_0(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 271
    A_DOUBLE_FLAT_1(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 272
    A_DOUBLE_FLAT_2(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 273
    A_DOUBLE_FLAT_3(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 274
    A_DOUBLE_FLAT_4(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 275
    A_DOUBLE_FLAT_5(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 276
    A_DOUBLE_FLAT_6(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 277
    A_DOUBLE_FLAT_7(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 278
    A_DOUBLE_FLAT_8(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 279
    A_DOUBLE_FLAT_9(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 280
    A_DOUBLE_FLAT_10(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_10),                                 // 281

    A_FLAT_0(PitchClass.A_FLAT, Octave.OCTAVE_0),                                                 // 282
    A_FLAT_1(PitchClass.A_FLAT, Octave.OCTAVE_1),                                                 // 283
    A_FLAT_2(PitchClass.A_FLAT, Octave.OCTAVE_2),                                                 // 284
    A_FLAT_3(PitchClass.A_FLAT, Octave.OCTAVE_3),                                                 // 285
    A_FLAT_4(PitchClass.A_FLAT, Octave.OCTAVE_4),                                                 // 286
    A_FLAT_5(PitchClass.A_FLAT, Octave.OCTAVE_5),                                                 // 287
    A_FLAT_6(PitchClass.A_FLAT, Octave.OCTAVE_6),                                                 // 288
    A_FLAT_7(PitchClass.A_FLAT, Octave.OCTAVE_7),                                                 // 289
    A_FLAT_8(PitchClass.A_FLAT, Octave.OCTAVE_8),                                                 // 290
    A_FLAT_9(PitchClass.A_FLAT, Octave.OCTAVE_9),                                                 // 291

    A_0(PitchClass.A, Octave.OCTAVE_0),                                                           // 292
    A_1(PitchClass.A, Octave.OCTAVE_1),                                                           // 293
    A_2(PitchClass.A, Octave.OCTAVE_2),                                                           // 294
    A_3(PitchClass.A, Octave.OCTAVE_3),                                                           // 295
    A_4(PitchClass.A, Octave.OCTAVE_4),                                                           // 296
    A_5(PitchClass.A, Octave.OCTAVE_5),                                                           // 297
    A_6(PitchClass.A, Octave.OCTAVE_6),                                                           // 298
    A_7(PitchClass.A, Octave.OCTAVE_7),                                                           // 299
    A_8(PitchClass.A, Octave.OCTAVE_8),                                                           // 300
    A_9(PitchClass.A, Octave.OCTAVE_9),                                                           // 301

    A_SHARP_0(PitchClass.A_SHARP, Octave.OCTAVE_0),                                               // 302
    A_SHARP_1(PitchClass.A_SHARP, Octave.OCTAVE_1),                                               // 303
    A_SHARP_2(PitchClass.A_SHARP, Octave.OCTAVE_2),                                               // 304
    A_SHARP_3(PitchClass.A_SHARP, Octave.OCTAVE_3),                                               // 305
    A_SHARP_4(PitchClass.A_SHARP, Octave.OCTAVE_4),                                               // 306
    A_SHARP_5(PitchClass.A_SHARP, Octave.OCTAVE_5),                                               // 307
    A_SHARP_6(PitchClass.A_SHARP, Octave.OCTAVE_6),                                               // 308
    A_SHARP_7(PitchClass.A_SHARP, Octave.OCTAVE_7),                                               // 309
    A_SHARP_8(PitchClass.A_SHARP, Octave.OCTAVE_8),                                               // 310
    A_SHARP_9(PitchClass.A_SHARP, Octave.OCTAVE_9),                                               // 311

    A_DOUBLE_SHARP_0(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 312
    A_DOUBLE_SHARP_1(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 313
    A_DOUBLE_SHARP_2(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 314
    A_DOUBLE_SHARP_3(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 315
    A_DOUBLE_SHARP_4(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 316
    A_DOUBLE_SHARP_5(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 317
    A_DOUBLE_SHARP_6(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 318
    A_DOUBLE_SHARP_7(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 319
    A_DOUBLE_SHARP_8(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 320
    A_DOUBLE_SHARP_9(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 321



    B_DOUBLE_FLAT_0(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_0),                                   // 322
    B_DOUBLE_FLAT_1(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_1),                                   // 323
    B_DOUBLE_FLAT_2(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_2),                                   // 324
    B_DOUBLE_FLAT_3(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_3),                                   // 325
    B_DOUBLE_FLAT_4(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_4),                                   // 326
    B_DOUBLE_FLAT_5(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_5),                                   // 327
    B_DOUBLE_FLAT_6(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_6),                                   // 328
    B_DOUBLE_FLAT_7(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_7),                                   // 329
    B_DOUBLE_FLAT_8(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_8),                                   // 330
    B_DOUBLE_FLAT_9(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_9),                                   // 331

    B_FLAT_0(PitchClass.B_FLAT, Octave.OCTAVE_0),                                                 // 332
    B_FLAT_1(PitchClass.B_FLAT, Octave.OCTAVE_1),                                                 // 333
    B_FLAT_2(PitchClass.B_FLAT, Octave.OCTAVE_2),                                                 // 334
    B_FLAT_3(PitchClass.B_FLAT, Octave.OCTAVE_3),                                                 // 335
    B_FLAT_4(PitchClass.B_FLAT, Octave.OCTAVE_4),                                                 // 336
    B_FLAT_5(PitchClass.B_FLAT, Octave.OCTAVE_5),                                                 // 337
    B_FLAT_6(PitchClass.B_FLAT, Octave.OCTAVE_6),                                                 // 338
    B_FLAT_7(PitchClass.B_FLAT, Octave.OCTAVE_7),                                                 // 339
    B_FLAT_8(PitchClass.B_FLAT, Octave.OCTAVE_8),                                                 // 340
    B_FLAT_9(PitchClass.B_FLAT, Octave.OCTAVE_9),                                                 // 341

    B_0(PitchClass.B, Octave.OCTAVE_0),                                                           // 342
    B_1(PitchClass.B, Octave.OCTAVE_1),                                                           // 343
    B_2(PitchClass.B, Octave.OCTAVE_2),                                                           // 344
    B_3(PitchClass.B, Octave.OCTAVE_3),                                                           // 345
    B_4(PitchClass.B, Octave.OCTAVE_4),                                                           // 346
    B_5(PitchClass.B, Octave.OCTAVE_5),                                                           // 347
    B_6(PitchClass.B, Octave.OCTAVE_6),                                                           // 348
    B_7(PitchClass.B, Octave.OCTAVE_7),                                                           // 349
    B_8(PitchClass.B, Octave.OCTAVE_8),                                                           // 350
    B_9(PitchClass.B, Octave.OCTAVE_9),                                                           // 351

    B_SHARP_0(PitchClass.B_SHARP, Octave.OCTAVE_0),                                               // 352
    B_SHARP_1(PitchClass.B_SHARP, Octave.OCTAVE_1),                                               // 353
    B_SHARP_2(PitchClass.B_SHARP, Octave.OCTAVE_2),                                               // 354
    B_SHARP_3(PitchClass.B_SHARP, Octave.OCTAVE_3),                                               // 355
    B_SHARP_4(PitchClass.B_SHARP, Octave.OCTAVE_4),                                               // 356
    B_SHARP_5(PitchClass.B_SHARP, Octave.OCTAVE_5),                                               // 357
    B_SHARP_6(PitchClass.B_SHARP, Octave.OCTAVE_6),                                               // 358
    B_SHARP_7(PitchClass.B_SHARP, Octave.OCTAVE_7),                                               // 359
    B_SHARP_8(PitchClass.B_SHARP, Octave.OCTAVE_8),                                               // 360
    B_SHARP_9(PitchClass.B_SHARP, Octave.OCTAVE_9),                                               // 361
    B_SHARP_10(PitchClass.B_SHARP, Octave.OCTAVE_10),                                             // 362

    B_DOUBLE_SHARP_0(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_0),                                 // 363
    B_DOUBLE_SHARP_1(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_1),                                 // 364
    B_DOUBLE_SHARP_2(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_2),                                 // 365
    B_DOUBLE_SHARP_3(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_3),                                 // 366
    B_DOUBLE_SHARP_4(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_4),                                 // 367
    B_DOUBLE_SHARP_5(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_5),                                 // 368
    B_DOUBLE_SHARP_6(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_6),                                 // 369
    B_DOUBLE_SHARP_7(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_7),                                 // 370
    B_DOUBLE_SHARP_8(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_8),                                 // 371
    B_DOUBLE_SHARP_9(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_9),                                 // 372
    B_DOUBLE_SHARP_10(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_10),                               // 373
    ;

    public final PitchClass PITCH_CLASS;
    public final Octave OCTAVE;
    public final int ABSOLUTE_PITCH;

    private static final Pitch[] VALUES = Pitch.values();
    private static final Map<Integer, ArrayList<Pitch>> ENHARMONICS = new HashMap<>(127);
        static {
            for (int i = 0; i <= 127; i++) {
                ENHARMONICS.put(i, new ArrayList<>());
            }

            for (Pitch pitch : VALUES) {
                ENHARMONICS.get(pitch.ABSOLUTE_PITCH).add(pitch);
            }
        }

    /*
     * This array is used exclusively for the purpose of
     * grouping all enharmonic PitchClasses, which simplifies
     * the algorithm in transposeTo(PitchInterval).
     */
    private static final PitchClass[][] PITCH_CLASS_CANDIDATE_REFERENCE =
            {
                    PitchClass.C.getEnharmonics(),
                    PitchClass.C_SHARP.getEnharmonics(),
                    PitchClass.D.getEnharmonics(),
                    PitchClass.D_SHARP.getEnharmonics(),
                    PitchClass.E.getEnharmonics(),
                    PitchClass.F.getEnharmonics(),
                    PitchClass.F_SHARP.getEnharmonics(),
                    PitchClass.G.getEnharmonics(),
                    PitchClass.G_SHARP.getEnharmonics(),
                    PitchClass.A.getEnharmonics(),
                    PitchClass.A_SHARP.getEnharmonics(),
                    PitchClass.B.getEnharmonics(),
            };

    Pitch(PitchClass pitchClass, Octave octave) {
        this.PITCH_CLASS = pitchClass;
        this.OCTAVE = octave;
        this.ABSOLUTE_PITCH = octave.MIDI_START + pitchClass.BASE_MIDI_VALUE;
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return this.PITCH_CLASS.isDiatonicTo(keySignature);
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return this.PITCH_CLASS.isDiatonicTo(intervalSet);
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
    public boolean isTransposableTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        return direction
                ? ABSOLUTE_PITCH + pitchInterval.NUM_SEMITONES <= 127
                : ABSOLUTE_PITCH - pitchInterval.NUM_SEMITONES >= 0;
    }

    @Nullable
    @Override
    public Pitch transposeTo(@NotNull PitchInterval pitchInterval, boolean direction) {
        if (isTransposableTo(pitchInterval, direction)) {

            // The returned Pitch will contain this PitchClass
            PitchClass transposedPitchClass = null;

            // 1. Get the group of enharmonic PitchClasses. This is our candidate set.
            int idx = PITCH_CLASS.BASE_MIDI_VALUE;
            int enharmonicIndex = direction
                    ? (idx + pitchInterval.NUM_SEMITONES) % 12
                    : (idx + 12 - pitchInterval.NUM_SEMITONES) % 12;
            PitchClass[] candidates = PITCH_CLASS_CANDIDATE_REFERENCE[enharmonicIndex];

            // 2. Determine the expected letter of the returned Pitch. It may or may not exist in the candidate set.
            idx = PITCH_CLASS.ENHARMONIC_SPELLING.LETTER.ordinal();
            int expectedLetterIndex = direction
                    ? (idx + (pitchInterval.NUMBER - 1)) % 7
                    : (idx + (7 - (pitchInterval.NUMBER - 1))) % 7;
            Letter expectedLetter = Letter.values()[expectedLetterIndex];

            // 3. Choose a PitchClass from the candidate set.
            int shortestDistance = 7, indexWithShortestDistance = -1;
            for (int i = 0; i < candidates.length; i++) {
                if (candidates[i].ENHARMONIC_SPELLING.LETTER.equals(expectedLetter)) {
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
                int candidateLetterIndex = candidates[i].ENHARMONIC_SPELLING.LETTER.ordinal();
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

            /*
             * Substitute the non-natural equivalent for transposed PitchClasses with natural Accidentals,
             * and decide which Octave to use for the returned Pitch.
             */
            Pitch candidate = Pitch.valueOf(transposedPitchClass.ENHARMONIC_SPELLING.apply(Accidental.NONE).name() + "_" + OCTAVE.NUMBER);
            if (candidate.PITCH_CLASS.equals(PITCH_CLASS) || candidate.ABSOLUTE_PITCH == ABSOLUTE_PITCH) {
                return VALUES[direction ? candidate.ordinal() + 1 : candidate.ordinal() - 1];
            }
            else if (PITCH_CLASS.BASE_MIDI_VALUE < transposedPitchClass.BASE_MIDI_VALUE) {
                return direction ? candidate : VALUES[candidate.ordinal() - 1];
            }
            else if (PITCH_CLASS.BASE_MIDI_VALUE > transposedPitchClass.BASE_MIDI_VALUE) {
                return direction ? VALUES[candidate.ordinal() + 1] : candidate;
            }
            else return candidate;
        }
        else {
            return null; // Returned on error
        }
    }

    @Override
    public boolean isTransposableTo(@NotNull Octave octave) {
        return PITCH_CLASS.OCTAVE_RANGE.MIDI_START >= octave.MIDI_START;
    }

    @Nullable
    @Override
    public Pitch transposeTo(@NotNull Octave octave) {
        return isTransposableTo(octave)
                ? Pitch.valueOf(PITCH_CLASS.name() + "_" + octave.NUMBER)
                : null;
    }

    @Override
    public boolean isTransposableTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        try {
            Pitch.valueOf(pitchClass.name() + "_" + octave.NUMBER);

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

    @Nullable
    @Override
    public Pitch transposeTo(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return isTransposableTo(pitchClass, octave)
                ? Pitch.valueOf(pitchClass.name() + "_" + octave.NUMBER)
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

    @Override
    public Pitch transposeTo(@NotNull Pitch pitch) {
        return pitch;
    }
}
