# [ChordInnate](https://chordinnate.herokuapp.com) [![Build Status](https://travis-ci.org/jdchampion/ChordInnate.svg?branch=master)](https://travis-ci.org/jdchampion/ChordInnate) [![Release](https://jitpack.io/v/jdchampion/ChordInnate.svg)](https://jitpack.io/#jdchampion/ChordInnate) ![Deployment](http://heroku-badge.herokuapp.com/?app=chordinnate)


#### PHENOMENAL MUSIC POWER...
![alt text][1]

[1]: https://vignette.wikia.nocookie.net/disney-fan-fiction/images/1/18/Aladdin-disneyscreencaps.com-5096.jpg/revision/latest?cb=20130806160611 "PHENOMENAL MUSIC POWER"

...itty bitty JAR size!
![alt text][2]

[2]: https://memegenerator.net/img/images/12041137/genie-itty-bitty-living-space.jpg "itty bitty JAR size"

---
## What is ChordInnate?
**ChordInnate** is essentially a music theory engine and API. Similar to how a physics engine simulates physical objects and their interactions, ChordInnate models the structure, operation, and interactions of musical components. This can be useful for studying music theory in a sandbox environment, or to provide intelligence for an algorithmic music generator.

---
## Some Key Features
#### PITCH
  - 1000+ preset scale types and 50+ preset chord types, with support for user extensibility
  - A relational 'tagging' system to allow the user to group or associate chords and scales in any arbitrary way
  - All standard major & minor key signatures, with full support for theoretical keys
  - Declare pitches, keys, chords, or scales with any number of accidentals
    >```java
    >// All of these are valid
    >Pitch c4 = Pitch.withName("C4");
    >Pitch cFlat4 = Pitch.withName("Cb4");
    >Pitch cDoubleFlat4 = Pitch.withName("Cbb4");
    >Pitch cSharp4 = Pitch.withName("C#4");
    >Pitch cDoubleSharpImproper4 = Pitch.withName("C##4");
    >Pitch cDoubleSharp4 = Pitch.withName("Cx4");
    >Pitch cCrazyAccidentals4 = Pitch.withName("Cxbb#b#xbbb#b4"); //...seriously.
    >```
  - Transpose pitches, scales, and chords by providing:
    - pitch
    - pitch class + octave
    - direction + pitch class
    - direction + interval
    >```java
    >// C4 -> G4
    >Pitch.withName("C4").transpose(Pitch.withName("G4"));
    >Pitch.withName("C4").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Pitch.withName("C4").transpose(IntervalDirection.UP, PitchClass.G);
    >Pitch.withName("C4").transpose(IntervalDirection.UP, Interval.PERFECT_5);
    >
    >// C Major (C, D, E, F, G, A, B) -> G Major (G, A, B, C, D, E, F#)
    >Scale("C Major").transpose(Pitch.withName("G4"));
    >Scale("C Major").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Scale("C Major").transpose(IntervalDirection.UP, Interval.PERFECT_5);
    >
    >// Cmaj7 (C, E, G, B) -> Gmaj7 (G, B, D, F#)
    >Chord("Cmaj7").transpose(Pitch.withName("G4"));
    >Chord("Cmaj7").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Chord("Cmaj7").transpose(IntervalDirection.UP, Interval.PERFECT_5);
    >```
  - Add or subtract intervals
    >```java
    >Interval.PERFECT_4.plus(Interval.PERFECT_5);    // perfect octave
    >Interval.PERFECT_8.minus(Interval.PERFECT_5);   // perfect fourth
    >```
  - Extensive and highly-expressive notation for Roman Numerals
    >```java
    >// Several examples:
    >RomanNumeral.withSymbol("{I}");                     // major degree 1
    >RomanNumeral.withSymbol("{ii}");                    // minor degree 2
    >RomanNumeral.withSymbol("{III+}");                  // augmented degree 3
    >RomanNumeral.withSymbol("{viio}");                  // diminished degree 7
    >RomanNumeral.withSymbol("{bIV}");                   // major of flat 4 degree
    >RomanNumeral.withSymbol("{XII}");                   // major degree 12
    >RomanNumeral.withSymbol("{I(6/4)}");                // 2nd inversion of major degree 1
    >RomanNumeral.withSymbol("{V7}/{V}");                // secondary dominant
    >RomanNumeral.withSymbol("{ii}/{VII+}/{I(6/5)}");    // ridiculous auxiliaries
    >// ... and so on.
    >```
  - Derive the Roman Numeral analysis for any scale
    >```java
    >Scale("C Major").getRomanNumeralAnalysis();             // I ii iii IV V vi vii˚
    >Scale("C Harmonic Minor").getRomanNumeralAnalysis();    // i ii˚ III+ iv V VI vii˚
    >```
  - Invert chords to any permutation
    >```java
    >Chord chord = new Chord("Cmaj7")        // 1st inversion: Cmaj7 (C, E, G, B)
    >    .invert()                           // 2nd inversion: Cmaj7/E (E, G, B, C)
    >    .invert()                           // 3rd inversion: Cmaj7/G (G, B, C, E)
    >    .invert()                           // 4th inversion: Cmaj7/B (B, C, E, G)
    >    .invert();                          // 1st inversion: Cmaj7 (C, E, G, B)
    >```
  - Invert intervals within the same octave
    >```java
    >Interval.PERFECT_4.invert()             // perfect fifth
    >   .invert();                           // perfect fourth
    >
    >Interval.DIMINISHED_10.invert()         // augmented 13th
    >   .invert();                           // diminished 10th
    >
    >Interval.MINOR_2.invert()               // major seventh
    >   .invert();                           // minor second
    >
    >Interval.withName("AA4").invert()       // doubly-diminished fifth
    >   .invert();                           // doubly-augmented fourth
    >```
  - Modulate key signatures any number of times
    >```java
    >KeySignature.withName("C Major")
    >   .modulateFlat()                  // F Major
    >   .modulateFlat()                  // Bb Major
    >   .modulateFlat();                 // Eb Major
    >
    >KeySignature.withName("C Major")
    >   .modulateSharp()                 // G Major
    >   .modulateSharp()                 // D Major
    >   .modulateSharp();                // A Major
    >```
  - Get relative and parallel keys
    >```java
    >KeySignature.withName("C Major")
    >   .getRelativeKey();               // A Minor
    >
    >KeySignature.withName("C Major")
    >   .getParallelKey();               // C Minor
    >```
  - Check diatonality of pitches, chords, and scales
    >```java
    >Chord("Cmaj7").isDiatonicTo(KeySignature.withName("C Major")); // true
    >Chord("Cmaj7").isDiatonicTo(KeySignature.withName("A Minor")); // true
    >Chord("Cmaj7").isDiatonicTo(KeySignature.withName("D Major")); // false
    >```
  - Check enharmonics of pitches
    >```java
    >Pitch.withName("C4").isEnharmonicTo(PitchClass.withName("B#"));         // true
    >PitchClass.withName("F#").isEnharmonicTo(PitchClass.withName("Gb"));    // true
    >```
  - Find semitones between two pitch classes
    >```java
    >PitchClass.getSemitoneDistanceBetween(PitchClass.C, PitchClass.D);      // 2
    >```

---

#### RHYTHM
  - Presets for all standard beat durations (128th note to double-whole note)
  - Support for any (valid) custom beat duration, including tuplets or dot modifications to the beat

---

#### METER
  - 20 standard tempo presets, with support for customized tempo BPM
  - Support (and automatic classification) for meter types:
      - simple
      - compound
      - complex
      - complete
      - additive
      - multiplicative
      - fixed
      - alternating
      - mixed
      - perfect
      - imperfect
      - odd
      - irregular
      - asymmetrical
      - fractional
      - free

---
## TODO
*This is an (ever-growing) list of features that are planned or currently in the works for ChordInnate:*
- [x] ~~User-extensible chords and scales~~
- [x] ~~Support for dyads, triads, tetrads, etc.~~
- [ ] Interval ratios (Just Intonation, 12-Tone Equal Temperament)
- [ ] Support for user-entered chord symbols for Roman Numeral ({Isus4}, etc.)
- [ ] Theory behind Shoenberg's 12-tone technique (tone rows, retrograde inversion)
- [ ] Borrowed chords
- [ ] Chord inference based on scale and Roman Numeral
- [ ] Nashville Numbering system
- [x] ~~Support any beat duration~~
- [ ] Cadence detection and classification
- [ ] Support for polymeter
- [ ] Support for microtones

---
## Project History and Motivation for Development
ChordInnate has been an ongoing project since the completion of my freshman year of Computer Science (2013). As a student making a jump from the arts to the sciences, I sought a challenging engineering project that would allow me to hone both my musical and technical skills.

The project originally began as a basic music generator, focusing on chord progressions. By the end of Summer 2013, a working prototype had been developed. While it was exciting to have met my goal, it was clear to me that the program's generation algorithm required more sophistication than `java.util.Random` -- *because that just didn't __sound__ all that great!* 

Since then, ChordInnate has undergone several iterations, shaping it into an *engine* that can be used for generating or analyzing music. I've incorporated many software engineering concepts and principles that I've encountered during my studies at work / school, and continue to enjoy improving my skills with this project.
