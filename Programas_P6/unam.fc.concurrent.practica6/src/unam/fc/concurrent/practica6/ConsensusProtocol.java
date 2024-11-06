package unam.fc.concurrent.practica6;

/*
 * Todo protocolo de consenso tiene un metodo decide(value) que propone un value y devuelve el valor ganador
 */
public interface ConsensusProtocol<T> {
    public T decide(T value, int me);
}
