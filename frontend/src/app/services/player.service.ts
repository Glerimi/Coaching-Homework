import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DatabasePlayer {
  id: number;
  name: string;
  position: string;
  status: string;
  jerseyNumber: number;
}

@Injectable({ providedIn: 'root' })
export class PlayerService {
  private readonly apiUrl = 'http://localhost:8080/api/db/players';

  constructor(private http: HttpClient) {}

  getPlayers(): Observable<DatabasePlayer[]> {
    return this.http.get<DatabasePlayer[]>(this.apiUrl);
  }
}
