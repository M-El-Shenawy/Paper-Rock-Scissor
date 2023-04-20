import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GameRequest } from './game.gameRequest';
import { Choice } from './game.choice';
import { GameResultResponse } from './game.gameResultResponse';
import { interval } from 'rxjs';

interface GameResponse {
  result: string;
}

interface GameStatisticsResponse {
  totalRounds: number;
  totalWinsForPlayer1: number;
  totalWinsForPlayer2: number;
  totalDraws: number;
}

@Component({
  selector: 'app-game',
  template: '<div class="buttons"> <button (click)="play()">Play</button> <button (click)="reset()">Reset</button> </div> <div class="game-stats"> <p>Your choice is: {{ move }}</p> <p>Number of rounds played: {{ roundsPlayed }}</p> <p>Game result: {{ result }}</p> <table class="stats-table"> <thead> <tr> <th>Player One Wins</th> <th>Player Two Wins</th> <th>Draws</th> </tr> </thead> <tbody> <tr> <td>{{ gameStats.playerOneWins }}</td> <td>{{ gameStats.playerTwoWins }}</td> <td>{{ gameStats.draws }}</td> </tr> </tbody> </table> </div> <div class="total-stats"> <table class="stats-table"> <thead> <tr> <th>Total Number Of Player One Wins</th> <th>Total Number Of Player Two Wins</th> <th>Total Number Of Draws</th> <th>Total Number Of Rounds played </th> </tr> </thead> <tbody> <tr> <td>{{ gameStatistics.totalWinsForPlayer1 }}</td> <td>{{ gameStatistics.totalWinsForPlayer2 }}</td> <td>{{ gameStatistics.totalDraws }}</td> <td>{{ gameStatistics.totalRounds }}</td> </tr> </tbody> </table> </div>',
  styles: [`
.game-stats {
  margin-bottom: 20px;
}

.stats-table {
  border-collapse: collapse;
  margin-bottom: 10px;
}

.stats-table th, .stats-table td {
  border: 1px solid black;
  padding: 5px;
  text-align: center;
}
`]
})
export class GameComponent implements OnInit {
  result: string | null = null;
  roundsPlayed = 0;
  move: string | null = null;
  public gameStats: { playerOneWins: number, playerTwoWins: number, draws: number } = { playerOneWins: 0, playerTwoWins: 0, draws: 0 };
  public gameStatistics: { totalWinsForPlayer1: number, totalWinsForPlayer2: number, totalDraws: number, totalRounds: number } =
    { totalWinsForPlayer1: 0, totalWinsForPlayer2: 0, totalDraws: 0, totalRounds: 0 };
  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.getGameStatistics();
    interval(2000).subscribe(() => {
      this.getGameStatistics();
    });
  }

  play(): void {
    const choices = Object.values(Choice);
    const player1Move = choices[Math.floor(Math.random() * choices.length)];
    const player2Move = choices[2]; // always play rock
    this.move = player1Move.toString();
    const request = new GameRequest(player1Move as Choice, player2Move as Choice);
    this.http.post<GameResultResponse>('http://localhost:5432/api/game', request).subscribe(
      (response) => {
        this.result = response.winner;
        this.handleGameResponse(response.winner);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  private handleGameResponse(response: string): void {
    this.result = response;
    this.roundsPlayed += 1;

    if (response === "PLAYER_ONE") {
      this.gameStats.playerOneWins += 1;
    } else if (response === "PLAYER_TWO") {
      this.gameStats.playerTwoWins += 1;
    } else {
      this.gameStats.draws += 1;
    }
    this.getGameStatistics();
  }

  private getGameStatistics(): void {
    this.http.get<GameStatisticsResponse>('http://localhost:5432/api/game').subscribe(
      (response) => {
        this.gameStatistics.totalWinsForPlayer1 = response.totalWinsForPlayer1;
        this.gameStatistics.totalWinsForPlayer2 = response.totalWinsForPlayer2;
        this.gameStatistics.totalDraws = response.totalDraws;
        this.gameStatistics.totalRounds = response.totalRounds;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  reset(): void {
    this.result = '';
    this.move = '';
    this.roundsPlayed = 0;
    this.gameStats = { playerOneWins: 0, playerTwoWins: 0, draws: 0 };
  }

}
