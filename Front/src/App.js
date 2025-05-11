import 'bootstrap/dist/css/bootstrap.min.css';
import { Col, Container, Row, Button, ButtonGroup, Spinner} from 'react-bootstrap';
import { useState, useEffect, useCallback } from 'react';
import RegistroJornada from "./Modulos/RegistroJornada"
import Footer from "./Modulos/Footer"
import Permisos from './Modulos/Permisos';
import Usuario from './Modulos/Usuario';
import Login from './Modulos/Login';
import logo from "./assets/logo.png";

function App() {

  const [moduloActivo, setModuloActivo] = useState('login');
  const [apiData, setApiData] = useState({nombre : "Pedro", registros : [], permisos : []});
  const [loading, setLoading] = useState(false);

  const [reload, setReload] = useState(0);
  const refrescar = useCallback(() => setReload(r => r + 1), []);

      // cambia segun este autenticado
  const [isAuth, setIsAuth] = useState(false);
  const [user, setUser] = useState(null);
  const [email , setEmail] = useState('');

 

    // Fetch de la API
    useEffect(() => {

      const controller = new AbortController();

      setLoading(true);

      fetch('http://localhost:8080/empleado/usuario', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email }),
        signal: controller.signal
      })
        .then(res => {
          if (!res.ok) throw new Error(`HTTP ${res.status}`);
          return res.json();
      })
        .then(data => {
          console.log('Respuesta backend →', data);
          setApiData(data);
      })
        .catch(err => {
          if (err.name !== 'AbortError') console.error('Error al enviar datos:', err);
      })
        .finally(() => setLoading(false));

        // Cleanup si el componente se desmonta
        return () => controller.abort();

      }, [email, reload]);
  
    if (loading) return (
      <Container className='text-center'>
        <Spinner animation="border" variant="success" />
      </Container>
    );

    const handleLoginSuccess = (email) => {
      setIsAuth(true);
      setModuloActivo('inicio');
      setEmail(email);
  };

  return (

    <Container>
      
      {moduloActivo !== 'login' && (
            <Row id="cabecera">
              <Row>
                <Col>
                  <img style={{width: "200px"}} src={logo} alt='logo de la empresa'></img>
                </Col>
              </Row>
              <Row className='bg-success'>
                <Col className="d-flex justify-content-center">
                  <ButtonGroup aria-label="Basic example">
                    <Button variant="success" onClick={() => setModuloActivo('inicio')}>Inicio</Button>
                    <Button variant="success" onClick={() => setModuloActivo('jornada')}>Registros de Jornada</Button>
                    <Button variant="success" onClick={() => setModuloActivo('permisos')}>Permisos</Button>
                  </ButtonGroup>  
                </Col>
              </Row>
            </Row>
      )}


    {/* Cuerpo de la web */} 
      {moduloActivo === 'login' && (
        <Row className="d-flex justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
          <Login onSuccess={handleLoginSuccess} />
        </Row>
      )}

    {moduloActivo === 'inicio' && (
      <Row className='text-center'>
          <RegistroJornada data={apiData}
                            onRefresh={refrescar} />
          <Permisos data={apiData} 
                    onRefresh={refrescar}/>
          <Usuario data={apiData} />
      </Row>
      )}

    {moduloActivo === 'jornada' && (
      <Row className='text-center'>
          <RegistroJornada data={apiData}
                          onRefresh={refrescar} />
      </Row>
    )}

    {moduloActivo === 'permisos' && (
      <Row className='text-center'>
          <Permisos data={apiData} 
                    onRefresh={refrescar} />
      </Row>
    )}

      <Footer />

    </Container>
  );
}

export default App;
