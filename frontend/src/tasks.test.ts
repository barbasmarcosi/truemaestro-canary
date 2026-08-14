import { afterEach, describe, expect, it, vi } from 'vitest'
import { labelFor, loadTasks } from './tasks'

const REFERENCE_DATE = '2026-08-13'

const EXACT_TASK = {
  id: 1,
  title: 'Ship canary',
  dueDate: '2026-08-13',
  dueStatus: 'due_today',
} as const

describe('labelFor', () => {
  it('labels the exact task object as "Due today"', () => {
    expect(labelFor(EXACT_TASK)).toBe('Due today')
  })
})

describe('loadTasks', () => {
  afterEach(() => {
    vi.unstubAllGlobals()
  })

  it('fetches /api/tasks/due?referenceDate=2026-08-13 with a minimal fetch stub', async () => {
    const fetchStub = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => [EXACT_TASK],
    })
    vi.stubGlobal('fetch', fetchStub)

    const tasks = await loadTasks(REFERENCE_DATE)

    expect(fetchStub).toHaveBeenCalledWith(
      '/api/tasks/due?referenceDate=2026-08-13',
    )
    expect(tasks).toEqual([EXACT_TASK])
  })
})
