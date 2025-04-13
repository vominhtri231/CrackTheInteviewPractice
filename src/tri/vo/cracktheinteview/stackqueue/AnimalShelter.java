package tri.vo.cracktheinteview.stackqueue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class AnimalShelter {

    interface Animal {
    }

    static class Cat implements Animal {
    }

    static class Dog implements Animal {
    }

    Queue<Cat> cats = new LinkedList<>();
    Queue<Dog> dogs = new LinkedList<>();

    void enqueue(Animal animal) {
        if (animal instanceof Cat) {
            cats.add((Cat) animal);
            return;
        }

        if (animal instanceof Dog) {
            dogs.add((Dog) animal);
            return;
        }

        throw new IllegalArgumentException("Unsupported animal type");
    }

    Dog dequeueDogs() {
        return dogs.remove();
    }

    Cat dequeueCats() {
        return cats.remove();
    }

    Animal dequeue() {
        if (cats.isEmpty() && dogs.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (cats.isEmpty()) {
            return dequeueDogs();
        }

        if (dogs.isEmpty()) {
            return dequeueCats();
        }

        return dequeueCats();
    }
}
