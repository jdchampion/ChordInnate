# ChordInnate

#### PHENOMENAL MUSIC POWER...
![alt text][1]

[1]: https://vignette.wikia.nocookie.net/disney-fan-fiction/images/1/18/Aladdin-disneyscreencaps.com-5096.jpg/revision/latest?cb=20130806160611 "PHENOMENAL MUSIC POWER"

...itty bitty JAR size!
![alt text][2]

[2]: https://memegenerator.net/img/images/12041137/genie-itty-bitty-living-space.jpg "itty bitty living space"

### What is ChordInnate?
**ChordInnate** is essentially a music theory engine and API. Similar to how a physics engine simulates physical objects and their interactions, ChordInnate models the structure, operation, and interactions of musical components. This can be useful for studying music theory in a sandbox environment, or to provide intelligence for an algorithmic music generator.


Here's a list of ChordInnate's features (with more to come):
- **Pitch**
  - 1000+ scale types and 50+ chord types
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
  - Transpose pitches, scales, and chords with any:
    - pitch
    - pitch class + octave
    - direction + pitch class
    - direction + interval
    >```java
    >// C4 -> G4
    >Pitch.withName("C4").transpose(Pitch.withName("G4"));
    >Pitch.withName("C4").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Pitch.withName("C4").transpose(true, PitchClass.G);
    >Pitch.withName("C4").transpose(true, Interval.PERFECT_5);
    >
    >// C Major (C, D, E, F, G, A, B) -> G Major (G, A, B, C, D, E, F#)
    >Scale("C Major").transpose(Pitch.withName("G4"));
    >Scale("C Major").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Scale("C Major").transpose(true, Interval.PERFECT_5);
    >
    >// Cmaj7 (C, E, G, B) -> Gmaj7 (G, B, D, F#)
    >Chord("Cmaj7").transpose(Pitch.withName("G4"));
    >Chord("Cmaj7").transpose(PitchClass.G, Octave.OCTAVE_4);
    >Chord("Cmaj7").transpose(true, Interval.PERFECT_5);
    >```
  - Invert chords to any permutation
    >```java
    >Chord chord = new Chord("Cmaj7");       // 1st inversion: Cmaj7 (C, E, G, B)
    >chord.invert();                         // 2nd inversion: Cmaj7/E (E, G, B, C)
    >chord.invert();                         // 3rd inversion: Cmaj7/G (G, B, C, E)
    >chord.invert();                         // 4th inversion: Cmaj7/B (B, C, E, G)
    >chord.invert();                         // 1st inversion: Cmaj7 (C, E, G, B)
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
    >KeySignature.withname("C Major")
    >   .modulateFlat()                  // F Major
    >   .modulateFlat()                  // Bb Major
    >   .modulateFlat();                 // Eb Major
    >
    >KeySignature.withname("C Major")
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
- **Rhythm**
  - Support (and automatic classification) for meter types:
    - simple
    - compound
    - complex
    - perfect
    - imperfect
    - odd
    - irregular
    - asymmetrical
    - free
  - Automatic classification of meter type based on time signature
  - Support for all standard beat durations (64th note to double-whole note), as well as any tuplet or dot modifications to the beat
  - 20 standard tempo presets, with support for customized tempo BPM


### Project History and Motivation for Development
ChordInnate has been an ongoing project since the completion of my freshman year of Computer Science (2013). As a student making a jump from the arts to the sciences, I sought a challenging engineering project that would allow me to hone both my musical and technical skills.

The project originally began as a basic music generator, focusing on chord progressions. By the end of Summer 2013, a working prototype had been developed. While it was exciting to have met my goal, it was clear to me that the program's generation algorithm required more sophistication than `java.util.Random` -- *It just didn't __sound__ all that great!* 

Since then, ChordInnate has undergone several iterations, shaping it into an *engine* that can be used for generating or analyzing music. I've incorporated many software engineering concepts and principles that I've encountered during my studies at university, and continue to enjoy improving my skills with this project.
