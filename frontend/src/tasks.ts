export type DueStatus = 'overdue' | 'due_today' | 'upcoming'

export interface TaskItem {
  id: number
  title: string
  dueDate: string
  dueStatus: DueStatus
}

const LABELS: Record<DueStatus, string> = {
  overdue: 'Overdue',
  due_today: 'Due today',
  upcoming: 'Upcoming',
}

export function labelFor(task: TaskItem): string {
  return LABELS[task.dueStatus]
}

export async function loadTasks(referenceDate: string): Promise<TaskItem[]> {
  const response = await fetch(
    `/api/tasks/due?referenceDate=${encodeURIComponent(referenceDate)}`,
  )
  if (!response.ok) {
    throw new Error(`Failed to load tasks: ${response.status}`)
  }
  return response.json()
}