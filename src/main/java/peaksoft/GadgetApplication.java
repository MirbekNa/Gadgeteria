
package peaksoft;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication


public class GadgetApplication {

    public static void main(String[] args) {

        SpringApplication.run(GadgetApplication.class, args);
Random random = new Random();
int random1= random.nextInt(100);
        System.out.println("Gadget works "+random1);

    }

}
