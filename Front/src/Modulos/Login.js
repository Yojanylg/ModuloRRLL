import { useState } from 'react';
import PropTypes from 'prop-types';
import {
  Col,
  Card,
  Form,
  Button,
  Alert,
  Spinner,
} from 'react-bootstrap';

/**
 * Componente de inicio de sesión.
 * Al autenticarse con éxito llama a `onSuccess` pasando el token (String)
 * devuelto por la API y el correo electrónico introducido.
 */
export default function Login({ onSuccess }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  /**
   * Maneja el envío del formulario.
   * Si la autenticación es correcta, notifica al componente padre con
   * el token recibido y el email introducido.
   */
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await fetch('http://localhost:8080/empleado/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });

      // La API devuelve texto plano ➜ leemos con .text()
      const body = await response.text();

      if (response.ok) {
        onSuccess(email);
      } else {
        // 'body' contiene el mensaje de error del backend
        setError(body || 'Usuario o contraseña incorrectos');
      }
    } catch (err) {
      console.error(err);
      setError('Error de conexión con el servidor');
    } finally {
      setLoading(false);
    }
  };

  /* ---------- Formulario de login ---------- */
  return (
    <Col xs={11} sm={8} md={6} lg={4}>
      <Card className="shadow">
        <Card.Body>
          <Card.Title className="text-center mb-4">Iniciar sesión</Card.Title>

          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="loginEmail">
              <Form.Control
                type="email"               // ✔ semántico
                autoComplete="username"
                placeholder="Correo electrónico"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="loginPassword">
              <Form.Control
                type="password"
                autoComplete="current-password"
                placeholder="Contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </Form.Group>

            {error && (
              <Alert
                variant="danger"
                aria-live="assertive"      // ✔ accesibilidad
                className="py-2 text-center"
              >
                {error}
              </Alert>
            )}

            <div className="d-grid">
              <Button
                variant="primary"
                type="submit"
                disabled={loading || !email || !password}  // ✔ UX
              >
                {loading ? (
                  <>
                    <Spinner animation="border" size="sm" className="me-2" />
                    Verificando…
                  </>
                ) : (
                  'Entrar'
                )}
              </Button>
            </div>
          </Form>
        </Card.Body>
      </Card>
    </Col>
  );
}

Login.propTypes = {
  /**
   * Callback que recibe (token: string, email: string) cuando el login es exitoso.
   */
  onSuccess: PropTypes.func.isRequired,
};