import { Choice } from "./game.choice";

export class GameRequest{
  player1Move: string;
  player2Move: string;
  constructor(player1Move: Choice, player2Move: Choice){
    this.player1Move = player1Move.toString();
    this.player2Move = player2Move.toString();
  }
}
