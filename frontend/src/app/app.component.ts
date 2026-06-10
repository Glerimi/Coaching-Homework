import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { PlayersComponent } from './players/players.component';

type Page = 'dashboard' | 'players';
type Player = { name: string; position: string; status: string };
type Stats = { activePlayers: number; teamEfficiency: number; upcomingPractice: string };

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PlayersComponent],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  page: Page = 'dashboard';
  stats: Stats = { activePlayers: 0, teamEfficiency: 0, upcomingPractice: '' };
  players: Player[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<Stats>('http://localhost:8080/api/stats').subscribe(data => this.stats = data);
    this.http.get<Player[]>('http://localhost:8080/api/players').subscribe(data => this.players = data);
  }
}
