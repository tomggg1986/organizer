export interface Task {
    id: number;
    notes: Note[];
}

export interface Note {
    id: number;
    name: string;
    text: string;
}
