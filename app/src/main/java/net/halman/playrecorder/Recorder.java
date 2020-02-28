/*
 * This file is part of PlayRecorder.
 *
 * PlayRecorder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlayRecorder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.halman.playrecorder;

import java.util.ArrayList;
import java.util.Arrays;

import static net.halman.playrecorder.Hole.BELLCLOSE;
import static net.halman.playrecorder.Hole.BELLOPEN;
import static net.halman.playrecorder.Hole.CLOSE;
import static net.halman.playrecorder.Hole.HALFOPEN;
import static net.halman.playrecorder.Hole.OPEN;
import static net.halman.playrecorder.Orientation.DOWN;
import static net.halman.playrecorder.Orientation.UP;

public class Recorder extends MusicalInstrument {

    public final static int BAROQUE = 0;
    public final static int GERMAN = 1;

    private int recorder_fingering;
    private ArrayList<ArrayList<Grip>> grips = null;

    public Recorder(int type)
    {
        super(type);
        if (type < Constants.RECORDER_SOPRANINO_BAROQUE || type > Constants.RECORDER_BASS_GERMAN) {
            instrument_type = Constants.RECORDER_SOPRANO_BAROQUE;
        }

        number_of_holes = 11;
        fingering();
        setHoles();
        setLimits();
    }

    public Recorder()
    {
        super(Constants.RECORDER_SOPRANO_BAROQUE);
        number_of_holes = 11;
        fingering();
        setHoles();
        setLimits();
    }

    private void setHoles() {
        hole(UP, 0, 150, 1.5);
        hole(UP, 0, 220, 1.5);
        hole(UP, 0, 270, 1.5);
        hole(UP, 0, 320, 1.5);
        hole(UP, 0, 390, 1.5);
        hole(UP, 0, 440, 1.5);
        hole(UP, -15, 490, 1.0);
        hole(UP, +15, 490, 1.0);
        hole(UP, -15, 530, 1.0);
        hole(UP, +15, 530, 1.0);
        hole(UP, 0, 580, 2.0);

        hole(DOWN, 0, 530, 1.5);
        hole(DOWN, 0, 460, 1.5);
        hole(DOWN, 0, 410, 1.5);
        hole(DOWN, 0, 360, 1.5);
        hole(DOWN, 0, 290, 1.5);
        hole(DOWN, 0, 240, 1.5);
        hole(DOWN, +15, 190, 1.0);
        hole(DOWN, -15, 190, 1.0);
        hole(DOWN, +15, 150, 1.0);
        hole(DOWN, -15, 150, 1.0);
        hole(DOWN, 0, 100, 2.0  );
    }

    private void setLimits() {
        switch (instrument_type) {
            case Constants.RECORDER_SOPRANINO_BAROQUE:
            case Constants.RECORDER_SOPRANINO_GERMAN:
                realLowestNote(new Note(Note.f5, Note.Accidentals.RELEASE));
                realHighestNote(new Note(Note.c7, Note.Accidentals.RELEASE));
                scoreOffset(-12);
                break;
            case Constants.RECORDER_SOPRANO_BAROQUE:
            case Constants.RECORDER_SOPRANO_GERMAN:
                realLowestNote(new Note(Note.c5, Note.Accidentals.RELEASE));
                realHighestNote(new Note(Note.g7, Note.Accidentals.RELEASE));
                scoreOffset(-12);
                break;
            case Constants.RECORDER_ALT_BAROQUE:
            case Constants.RECORDER_ALT_GERMAN:
                realLowestNote(new Note(Note.f4, Note.Accidentals.RELEASE));
                realHighestNote(new Note(Note.c6, Note.Accidentals.RELEASE));
                scoreOffset(0);
                break;
            case Constants.RECORDER_TENOR_BAROQUE:
            case Constants.RECORDER_TENOR_GERMAN:
                realLowestNote(new Note(Note.c4, Note.Accidentals.RELEASE));
                realHighestNote(new Note(Note.g6, Note.Accidentals.RELEASE));
                scoreOffset(0);
                break;
            case Constants.RECORDER_BASS_BAROQUE:
            case Constants.RECORDER_BASS_GERMAN:
                realLowestNote(new Note(Note.f3, Note.Accidentals.RELEASE));
                realHighestNote(new Note(Note.c5, Note.Accidentals.RELEASE));
                scoreOffset(12);
                break;
        }
    }

    private void fingering()
    {
        if (instrument_type <= Constants.RECORDER_BASS_BAROQUE ) {
            recorder_fingering = BAROQUE;
        } else {
            recorder_fingering = GERMAN;
        }
        switch (recorder_fingering) {
            case BAROQUE:
                setBaroqueGrips();
                break;
            case GERMAN:
                setGermanGrips();
                break;
        }
    }

    private Grip recorderGrip(int h0, int h1, int h2, int h3,
                              int h4, int h5, int h6, int h7,
                              int h8, int h9, int h10)
    {
        int [] gripArray = new int [] {h0, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10};

        return new Grip(gripArray);
    }

    private void setBaroqueGrips()
    {
        grips = new ArrayList<ArrayList<Grip>>();

        // C/F
        ArrayList<Grip> grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, BELLOPEN));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // B
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, HALFOPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, HALFOPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // B
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, BELLCLOSE));
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLCLOSE));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLCLOSE));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLCLOSE));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

    }

    private void setGermanGrips()
    {
        grips = new ArrayList<ArrayList<Grip>>();

        // C/F
        ArrayList<Grip> grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, BELLOPEN));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // B
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(CLOSE, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(OPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(OPEN, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, HALFOPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // A#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, OPEN, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLOPEN));
        grips.add(grip);

        // B
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // C#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, BELLCLOSE));
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, CLOSE, CLOSE, BELLCLOSE));
        grip.add(recorderGrip(CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, CLOSE, BELLCLOSE));
        grips.add(grip);

        // D
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, BELLOPEN));
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // D#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // E
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, OPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, CLOSE, OPEN, OPEN, BELLCLOSE));
        grips.add(grip);

        // F
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLCLOSE));
        grips.add(grip);

        // F#
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, CLOSE, OPEN, CLOSE, CLOSE, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

        // G
        grip = new ArrayList<Grip>();
        grip.add(recorderGrip(HALFOPEN, CLOSE, OPEN, OPEN, CLOSE, OPEN, OPEN, OPEN, OPEN, OPEN, BELLOPEN));
        grips.add(grip);

    }

    @Override
    public ArrayList<Grip> grips(Scale scale, Note apparentNote)
    {
        Note tmp = apparentNoteToRealNote(apparentNote);
        int idx = scale.noteAbsoluteValue(tmp) - scale.noteAbsoluteValue(realLowestNote());
        if ((idx >= 0) && (idx < grips.size())) {
            return grips.get(idx);
        }
        return null;
    }
}
