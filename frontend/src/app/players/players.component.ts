import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DatabasePlayer, PlayerService } from '../services/player.service';

@Component({
  selector: 'app-players',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './players.component.html'
})
export class PlayersComponent implements OnInit {
  players: DatabasePlayer[] = [];
  errorMessage = '';

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {
    this.playerService.getPlayers().subscribe({
      next: data => this.players = data,
      error: () => this.errorMessage = 'Players could not be loaded. Check if the backend and PostgreSQL are running.'
    });
  }
}
