# ChordInnate

### What is ChordInnate?
**ChordInnate** is essentially a music theory engine and API. Similar to how a physics engine simulates physical objects and their interactions, ChordInnate models the structure, operation, and interactions of musical components. This can be useful for studying music theory in a sandbox environment, or to provide intelligence for an algorithmic music generator.


Here's a list of ChordInnate's features (with more to come):
- **Pitch**
  - 1000+ scale types and 50+ chord types
  - all standard major & minor key signatures, with additional (limited) support for theoretical keys
  - transpose pitches directly to another pitch, or by pitch interval or pitch class
  - transpose scales and chords to any supported key
  - invert chords to any permutation
  - check whether pitch, chord, or scale is diatonic to key signature, chord, or scale
  - find all enharmonics for any given pitch
  - find semitones between two pitch classes
- **Rhythm**
  - support (and automatic classification) for meter types: simple, compound, complex, perfect, imperfect, odd, irregular, asymmetrical, and free
  - automatic classification of meter type based on time signature
  - support for a very wide range of beat values: 13-tuplet 64th note to 13-tuplet, 7-dotted double-whole note
  - 20 standard tempo presets, with support for customized tempo BPM


### Project History and Motivation for Development
ChordInnate has been an ongoing project since the completion of my freshman year of Computer Science (2013). As a student making a jump from the arts to the sciences, I sought a challenging engineering project that would allow me to hone both my musical and technical skills.

The project originally began as a basic music generator, focusing on chord progressions. By the end of Summer 2013, a working prototype had been developed. While it was exciting to have met my goal, it was clear to me that the program's generation algorithm required more sophistication than `java.util.Random` -- *It just didn't __sound__ all that great!* 

Since then, ChordInnate has undergone several iterations, shaping it into an *engine* that can be used for generating or analyzing music. I've incorporated many software engineering concepts and principles that I've encountered during my studies at university, and continue to enjoy improving my skills with this project.
