public class Hora {
    private final int horas;
    private final int minutos;
    private final int segundos;

    public Hora(int horas, int minutos, int segundos) {
        if (!validarHora(horas, minutos, segundos)) {
            throw new IllegalArgumentException("Hora inválida!");
        }
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    private boolean validarHora(int horas, int minutos, int segundos) {
        return (horas >= 0 && horas < 24) && (minutos >= 0 && minutos < 60) && (segundos >= 0 && segundos < 60);
    }

    public Hora incrementar(int valor, char unidade) {
        int novasHoras = this.horas;
        int novosMinutos = this.minutos;
        int novosSegundos = this.segundos;

        switch (unidade) {
            case 'h':
                novasHoras = (novasHoras + valor) % 24;
                break;
            case 'm':
                novosMinutos += valor;
                novasHoras = (novasHoras + novosMinutos / 60) % 24;
                novosMinutos %= 60;
                break;
            case 's':
                novosSegundos += valor;
                novosMinutos += novosSegundos / 60;
                novasHoras = (novasHoras + novosMinutos / 60) % 24;
                novosSegundos %= 60;
                novosMinutos %= 60;
                break;
            default:
                throw new IllegalArgumentException("Unidade inválida! Use 'h', 'm' ou 's'.");
        }
        return new Hora(novasHoras, novosMinutos, novosSegundos);
    }

    public boolean eDepoisDe(Hora outra) {
        if (this.horas != outra.horas) {
            return this.horas > outra.horas;
        }
        if (this.minutos != outra.minutos) {
            return this.minutos > outra.minutos;
        }
        return this.segundos > outra.segundos;
    }

    public String horaFormatada() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static void main(String[] args) {
        Hora hora1 = new Hora(7, 26, 21);
        Hora hora2 = new Hora(10, 1, 33);
        
        System.out.println("Hora1: " + hora1.horaFormatada());
        System.out.println("Hora2: " + hora2.horaFormatada());

        Hora novaHora = hora1.incrementar(100, 's');
        System.out.println("Hora1 + 100 segundos: " + novaHora.horaFormatada());

        if (hora1.eDepoisDe(hora2)) {
            System.out.println("Hora1 está depois de Hora2");
        } else {
            System.out.println("Hora2 está depois de Hora1");
        }
    }
}