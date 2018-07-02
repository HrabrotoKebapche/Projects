import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.*;

public class Gen2 {

		    Synthesizer synth;
		    UnitOscillator osc;
		    LineOut lineOut;

		    private void test(double hertz, double timePlay) {

		        // Create a context for the synthesizer.
		        synth = JSyn.createSynthesizer();

		        // Start synthesizer using default stereo output at 44100 Hz.
		        synth.start();
		       

		        // Add a tone Gen2erator.
		        synth.add(osc = new SineOscillator());
		        // Add a stereo audio output unit.
		        synth.add(lineOut = new LineOut());

		        // Connect the oscillator to both channels of the output.
		        osc.output.connect(0, lineOut.input, 0);
		        osc.output.connect(0, lineOut.input, 1);

		        // Set the frequency and amplitude for the sine wave.
		        osc.frequency.set(hertz);
		        osc.amplitude.set(100);

		        // We only need to start the LineOut. It will pull data from the
		        // oscillator.
		        lineOut.start();

		        System.out.println("You should now be hearing a sine wave. ---------");

		        // Sleep while the sound is Gen2erated in the background.
		        try {
		            double time = synth.getCurrentTime();
		            System.out.println("time = " + time);
		            // Sleep for a few seconds.
		            synth.sleepUntil(time + timePlay);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }

		        System.out.println("Stop playing. -------------------");
		        // Stop everything.
		        synth.stop();
		    }

		    public static void main(String[] args) {
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(246.94,0.8);
		        
		        new Gen2().test(0,0.4);
		        //second
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(233.08,0.4);
		        new Gen2().test(220.00,0.8);
		        
		        new Gen2().test(0,0.4);
		        //third
		        new Gen2().test(220.00,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(369.99,0.8);
		        new Gen2().test(0,0.4);
		        new Gen2().test(220.00,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(329.63,0.8);
		        
		        new Gen2().test(0,0.4);
		        //forth
		        new Gen2().test(164.81,0.4);
		        new Gen2().test(196.00,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(155.56,0.4);
		        new Gen2().test(164.81,0.8);
		        
		        new Gen2().test(0,0.4);
		        //fifth
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(293.66,0.8);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(246.94,0.8);
		        new Gen2().test(0,0.4);
		        
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(220.00,0.8);
		        new Gen2().test(0,0.4);
		        
		        new Gen2().test(220.00,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(233.08,0.4);
		        new Gen2().test(246.94,0.8);
		        new Gen2().test(0,0.4);
		        //sixth
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(246.94,0.8);
		        new Gen2().test(0,0.4);
		        //seventh
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(392.00,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(369.99,0.4);
		        new Gen2().test(329.63,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(233.08,0.4);
		        new Gen2().test(220.00,0.8);
		        new Gen2().test(0,0.4);
		        //eighth
		        new Gen2().test(220.00,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(369.99,0.8);
		        new Gen2().test(0,0.4);
		        //ninth
		        new Gen2().test(220.00,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(329.63,0.8);
		        new Gen2().test(0,0.4);
		        //tenth
		        new Gen2().test(164.81,0.4);
		        new Gen2().test(196.00,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(261.63,0.4);		        
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(293.66,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(261.63,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(246.94,0.4);
		        new Gen2().test(311.13,0.4);
		        new Gen2().test(329.63,1.6);
		        
		        
		}
}