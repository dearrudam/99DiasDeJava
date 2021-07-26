package noventaenovediasdejava.dia14;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ContadorTest {
    @Test
    @Order(1)
    void testarContadorUnsafe() throws InterruptedException {
        testar(
            new ContadorUnsafe(),
            50,
            1000,
            (contador, numeroDeTarefas) ->
                Assertions.assertNotEquals(numeroDeTarefas, contador.getValor())
        );
    }

    @Test
    @Order(2)
    void testarContadorSafe01() throws InterruptedException {
        testar(
            new ContadorSafe01(),
            50,
            1000,
            (contador, numeroDeTarefas) ->
                Assertions.assertEquals(numeroDeTarefas, contador.getValor())
        );
    }

    @Test
    @Order(3)
    void testarContadorSafe02() throws InterruptedException {
        testar(
            new ContadorSafe02(),
            50,
            1000,
            (contador, numeroDeTarefas) ->
                Assertions.assertEquals(numeroDeTarefas, contador.getValor())
        );
    }

    @Test
    @Order(4)
    void testarContadorSafe03() throws InterruptedException {
        testar(
            new ContadorSafe03(),
            50,
            1000,
            (contador, numeroDeTarefas) ->
                Assertions.assertEquals(numeroDeTarefas, contador.getValor())
        );
    }

    private void testar(
        final Contador contador,
        final int numeroDeThreads,
        final int numeroDeTarefas,
        final BiConsumer<Contador, Integer> asserts
    ) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numeroDeThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numeroDeTarefas);
        for (int tarefas = 0; tarefas < numeroDeTarefas; tarefas++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(50);
                    contador.incrementar();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        executorService.shutdown();
        countDownLatch.await(1, TimeUnit.MINUTES);
        asserts.accept(contador, numeroDeTarefas);
    }

    static interface Contador {
        void incrementar();
        int getValor();
    }

    static class ContadorUnsafe implements Contador {
        private int valor;

        @Override
        public void incrementar() {
            this.valor++; // this.valor = this.valor + 1
        }

        @Override
        public int getValor() {
            return this.valor;
        }
    }

    static class ContadorSafe01 implements Contador {
        private int valor;

        @Override
        public synchronized void incrementar() {
            this.valor++; // this.valor = this.valor + 1
        }

        @Override
        public synchronized int getValor() {
            return this.valor;
        }
    }

    static class ContadorSafe02 implements Contador {
        private int valor;

        @Override
        public void incrementar() {
            synchronized (this) {
                this.valor++; // this.valor = this.valor + 1
            }
        }

        @Override
        public int getValor() {
            synchronized (this) {
                return this.valor;
            }
        }
    }

    static class ContadorSafe03 implements Contador {
        private AtomicInteger valor = new AtomicInteger(0);

        @Override
        public void incrementar() {
            this.valor.incrementAndGet();
        }

        @Override
        public int getValor() {
            return this.valor.get();
        }
    }
}
