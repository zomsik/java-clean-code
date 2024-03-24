package pollub.projekt.ddd.common.patterns.interpreter;

import java.util.Date;

    /* Tydzień 6, Wzorzec Interpreter

    Wzorzec Interpreter pozwala na interpretowanie języka programowania poprzez definicje opisu gramatyki poprzez stworzony interpreter.
    Interpreter wykonuje wcześniej określone działania, tutaj na przykład tworzy datę z odpowiednim przesunięciem czasowym w przyszłość.

    Koniec, Tydzień 6, Wzorzec Interpreter  */

public class GetDateWithAddedSecondsExpression implements Expression {
    private final int seconds;

    public GetDateWithAddedSecondsExpression(int seconds) {
        this.seconds = seconds;
    }

    public GetDateWithAddedSecondsExpression(String seconds) {
        this.seconds = Integer.parseInt(seconds);
    }

    @Override
    public Date interpret() {
        Date currentDate = new Date();
        return new Date(currentDate.getTime() + (seconds * 1000L));
    }
}