# truemaestro-canary

A public, non-production canary repository. It exists to smoke-test the `quality`
CI pipeline with deterministic, fixture-bound code. It is not a product and must
never be pointed at production.

## Status

- Reference date: `2026-08-13` (fixed fixture used by backend and frontend tests).
- Backend: **expected RED**. `TaskClassifier.classify` never returns `DUE_TODAY`,
  so the `due_today` regression is intentionally failing:
  - `TaskClassifierTest.equalToReferenceDateIsDueToday` fails.
  - `TaskControllerTest.dueEndpointWithExplicitReferenceDateReturnsDueTodayForEqualFixture`
    fails.
  - Expected result of `./mvnw test`: exactly 2 failures, 0 errors.
- Frontend: **expected GREEN**. The following commands pass locally and in CI:
  ```sh
  cd frontend
  npm ci
  npm test
  npm run build
  ```
- CI: `.github/workflows/quality.yml` runs the `backend` and `frontend` jobs on
  every push and pull request.

## Boundaries

- No secrets, real data, or runtime payloads live in this repository.
- No outbound traffic, telemetry, or runtime requests are made by the backend
  application. Dependency installation may access Maven Central and npm.
- No deployment is configured; nothing here is deployed anywhere.

## First governed mission scope

The one intended change is limited to restoring equality in `TaskClassifier` so
an equal due date returns `DUE_TODAY`.

Changes to the controller or API shape, frontend, workflow, build system,
repository settings, non-working-day rules, and excluded dates are out of scope.
