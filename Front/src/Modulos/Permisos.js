import React, { useState, useMemo } from 'react';
import { Button, Col, Row, Table, Form } from 'react-bootstrap';
import SolicitudPermiso from './SolicitarPermiso';

function Permisos({ data = [], onRefresh }) {
  const [estadoModulo, setEstadoModulo] = useState('mostrar');
  const [anioSeleccionado, setAnioSeleccionado] = useState("");

  const manejarExito = () => {
    setEstadoModulo('mostrar')
    onRefresh?.();
  };

  const permisosFiltrados = useMemo(() => {
    if (!anioSeleccionado) return data.permisos;

    return data.permisos.filter(r => {
      const d = new Date(r.fechaInicio); // Convertir fecha de inicio a objeto Date
      return d.getFullYear().toString() === anioSeleccionado; // Filtrar por año
    });
  }, [anioSeleccionado, data.permisos]);

  return (
<>
      {estadoModulo === 'mostrar' && (
        <Col className="p-3" lg xl xxl={4} md={6} sm={12}>
          <Row className="bg-success">
            <h2>Permisos</h2>
          </Row>
          <Row className="p-1">
            <Row>
              <Col>
                <Row className="p-1">
                  <Button
                    variant="primary"
                    className="w-100"
                    onClick={() => setEstadoModulo('solicitando')}>Solicitar
                  </Button>
                </Row>
              </Col>
              <Col>
                <Row className="p-1">
                  <Button variant="primary" className="w-100">
                    Descargar
                  </Button>
                </Row>
              </Col>
              <Col>
                <Row className="p-1">
                  <Button variant="primary" className="w-100">
                    Anular
                  </Button>
                </Row>
              </Col>
            </Row>
            <Row className="p-1">
              <Col sm={4}>
                <Form.Control
                  value={anioSeleccionado}
                  placeholder="Año"
                  className="mr-sm-2"
                  onChange={e => setAnioSeleccionado(e.target.value)}
                />
              </Col>
            </Row>
          </Row>
          <Row className="p-1">
            <Table striped bordered hover size="sm">
              <thead>
                <tr>
                  <th>Fecha Inicio</th>
                  <th>Fecha Fin</th>
                  <th>Duración</th>
                  <th>Aprobado</th>
                </tr>
              </thead>
              <tbody>
                {permisosFiltrados.length > 0 ? (
                  permisosFiltrados.map((fila, idx) => (
                    <tr key={idx}>
                      <td>{fila.fechaInicio}</td>
                      <td>{fila.fechaFin}</td>
                      <td>{fila.duracion}</td>
                      <td>{fila.aprobado ? 'Aprobado' : 'Pendiente'}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4">No hay permisos disponibles para el año seleccionado</td>
                  </tr>
                )}
              </tbody>
            </Table>
          </Row>
          </Col>
      )}

      {estadoModulo === 'solicitando' && (
          <SolicitudPermiso onSuccess={manejarExito} />
      )}
</>
  );
}

export default Permisos;
