#### Heurísticas

- `nearest insertion`: primeiro escolhe a cidade que está mais próxima do circuito atual; depois decide em que posição essa cidade será inserida. Enfatiza a proximidade da próxima cidade ao circuito já construído.
- `smallest insertion`: prioriza diretamente a inserção que provoca o menor aumento no comprimento do circuito. Enfatiza o impacto da inserção no custo total do percurso.

## Compilacao e execucao

No diretorio `src`, execute:

```bash
javac Main.java Point.java Tour.java TSPVisualizer.java In.java StdIn.java StdOut.java StdDraw.java
java Main ../dados/usa13509.txt
```


#### Apresentação do trabalho
`link` 