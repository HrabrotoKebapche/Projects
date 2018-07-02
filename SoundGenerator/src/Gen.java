import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.*;

public class Gen {

		   Synthesizer synth;
		    UnitOscillator osc;
		    LineOut lineOut;

		    private void test(double hertz, double timePlay) {

		        // Create a context for the synthesizer.
		        synth = JSyn.createSynthesizer();

		        // Start synthesizer using default stereo output at 44100 Hz.
		        synth.start();

		        // Add a tone generator.
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

		        // Sleep while the sound is generated in the background.
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
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        
		        new Gen().test(207.65,0.15);
		        new Gen().test(207.65,0.15);
		        new Gen().test(207.65,0.15);
		        
		        new Gen().test(196.00,0.2);
		        
		        new Gen().test(0.00,0.8);
		        //second
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        
		        new Gen().test(207.65,0.2);
		        new Gen().test(246.94,0.3);
		        new Gen().test(261.63,0.4);
		        
		        new Gen().test(293.66,0.15);
		        new Gen().test(311.13,0.15);
		        new Gen().test(293.66,0.15);
		        
		        new Gen().test(261.63,0.2);
		        
		        new Gen().test(0.00,0.1);
		        //third
		        
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        
		        new Gen().test(207.65,0.15);
		        new Gen().test(207.65,0.15);
		        new Gen().test(207.65,0.15);
		        
		        new Gen().test(196.00,0.2);
		        
		        new Gen().test(0.00,0.6);
		        
		        //forth
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        new Gen().test(196.00,0.15);
		        
		        new Gen().test(207.65,0.2);
		        new Gen().test(246.94,0.3);
		        new Gen().test(261.63,0.4);
		        
		        new Gen().test(293.66,0.15);
		        new Gen().test(311.13,0.15);
		        new Gen().test(293.66,0.15);
		        //gordno re
		        new Gen().test(261.63,0.15);
		        //si
		        new Gen().test(246.94,0.15);
		        //gordno do
		        new Gen().test(261.63,0.15);
		        new Gen().test(0.00,0.2);
		        //fifth

//		    	//sol
//		    	 new Gen().test(392.00,0.15);
//		    	 //la
//		    	 new Gen().test(440.00,0.15);
//		    	 //fa
//		    	 new Gen().test(349.23,0.15);
//		    	 new Gen().test(349.23,0.15);
//		    	 new Gen().test(349.23,0.15);
//		        
//		    	 new Gen().test(440.00,0.15);
//		    	 new Gen().test(349.23,0.15);
//		    	 new Gen().test(440.00,0.15);
//		    	 new Gen().test(349.23,0.15);
		        
		        
		}
}