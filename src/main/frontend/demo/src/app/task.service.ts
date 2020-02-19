import { Injectable } from '@angular/core';
import { Task } from './datamodel/task';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  private taskUrl = 'http://localhost:8080/tasks';

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

  getTasks(name: string): Observable<Task[]>{
    const url = `${this.taskUrl}/?name=${name}`;
    return this.http.get<Task[]>(url)
            .pipe(
                  tap(_ => this.log('fetched Tasks')),
                   catchError(this.handleError<Task[]>('getTasks', []))
            );
  }

    private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {

        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead

        // TODO: better job of transforming error for user consumption
        this.log(`${operation} failed: ${error.message}`);

        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }

    private log(message: string) {
      this.messageService.add(`TaskService: ${message}`);
    }

}
