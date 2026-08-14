import { useEffect, useState } from 'react'
import { labelFor, loadTasks, type TaskItem } from './tasks'

const REFERENCE_DATE = '2026-08-13'

export default function App() {
  const [tasks, setTasks] = useState<TaskItem[]>([])
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    loadTasks(REFERENCE_DATE)
      .then(setTasks)
      .catch((err: unknown) =>
        setError(err instanceof Error ? err.message : String(err)),
      )
  }, [])

  return (
    <main>
      <h1>Tasks</h1>
      {error ? <p role="alert">{error}</p> : null}
      <ul>
        {tasks.map((task) => (
          <li key={task.id}>
            <span>{task.id}</span> <span>{task.title}</span>{' '}
            <span>{task.dueDate}</span> <span>{labelFor(task)}</span>
          </li>
        ))}
      </ul>
    </main>
  )
}