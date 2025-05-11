import React, { useState, useEffect } from 'react';
import { Button, Form, Container, Row, Col } from 'react-bootstrap';

const SolicitudPermiso = ({ onSuccess }) => {

  const [tiposPermiso, setTiposPermiso] = useState([]);
  const [selectedTipo, setSelectedTipo] = useState('');
  const [fechaInicio, setFechaInicio] = useState('');
  const [duracion, setDuracion] = useState('');
  const [idEmpleado, setIdEmpleado] = useState(1); // Asumimos que el idEmpleado es 1 por ahora
  const [mensaje, setMensaje] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validar que todos los campos estén completos
    if (!selectedTipo || !fechaInicio || !duracion) {
      setMensaje('Por favor complete todos los campos');
      return;
    }

    // Crear el objeto JSON que vamos a enviar
    const solicitudData = {
      idEmpleado,
      idTipoPermiso: selectedTipo,
      fechaInicio,
      duracion,
    };

try {
    const response = await fetch('http://localhost:8080/empleado/solicitarPermiso', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(solicitudData),
    });

    const data = await response.json();

    if (data) {

      setMensaje('Solicitud enviada con éxito');
      onSuccess();
    } else {

      setMensaje('Error al enviar la solicitud');
    }
    
  } catch (error) {
    console.error('Error al enviar la solicitud:', error);
    setMensaje('Hubo un error al enviar la solicitud');
  }
};


useEffect(() => {
    fetch('http://localhost:8080/tipoPermiso/all')
      .then(response => response.json())
      .then(data => setTiposPermiso(data))
      .catch(error => {
        console.error('Hubo un error al obtener los tipos de permiso:', error);
      });
  }, []);


  return (
    <Col className="p-3" id="solicitarPermisos" lg xl xxl={4} md={6} sm={12}>
        <Row className="bg-success"><h2>Solicitar Permiso</h2></Row>
        <Row>
            <Form onSubmit={handleSubmit}>
                <Row>
                <Col md={6}>
                    <Form.Group controlId="tipoPermiso">
                    <Form.Label>Tipo de Permiso</Form.Label>
                    <Form.Control
                        as="select"
                        value={selectedTipo}
                        onChange={(e) => setSelectedTipo(e.target.value)}
                    >
                        <option value="">Seleccione un tipo de permiso</option>
                        {tiposPermiso && tiposPermiso.map((tipo) => (
                        <option key={tipo.idTipo} value={tipo.idTipo}>
                            {tipo.nombre}
                        </option>
                        ))}
                    </Form.Control>
                    </Form.Group>
                </Col>
                <Col md={6}>
                    <Form.Group controlId="duracion">
                    <Form.Label>Duración (días)</Form.Label>
                    <Form.Control
                        type="number"
                        value={duracion}
                        onChange={(e) => setDuracion(e.target.value)}
                        placeholder="Duración en días"
                    />
                    </Form.Group>
                </Col>
                </Row>

                <Row>
                <Col md={6}>
                    <Form.Group controlId="fechaInicio">
                    <Form.Label>Fecha de Inicio</Form.Label>
                    <Form.Control
                        type="date"
                        value={fechaInicio}
                        onChange={(e) => setFechaInicio(e.target.value)}
                    />
                    </Form.Group>
                </Col>
                </Row>

                {mensaje && <p>{mensaje}</p>}

                <Button variant="primary" type="submit">
                Solicitar Permiso
                </Button>
            </Form>
        </Row>
    </Col>
  );
};

export default SolicitudPermiso;
