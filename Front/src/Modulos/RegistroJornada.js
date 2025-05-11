import 'bootstrap/dist/css/bootstrap.min.css';
import { Col, Row } from 'react-bootstrap';
import { useState, useMemo } from 'react';
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';

function RegistroJornada({ data = {}, onRefresh }) {
  
  const meses = [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                  "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

  const [mesSeleccionado, setMesSeleccionado] = useState("Mes");
  const [anioSeleccionado, setAnioSeleccionado] = useState("");

  const handleEntrada = async () => {
    try {
      await fetch (`http://localhost:8080/empleado/entrada?id=${data.idEmpleado}`, {
        method: "POST"
      });
      onRefresh?.();
    } catch (error) {
      console.error(error);
      alert("No se pudo registrar la entrada");
    } 
  }

  const handleSalida = async () => {
    try {
      await fetch (`http://localhost:8080/empleado/salida?id=${data.idEmpleado}`, {
        method: "POST"
      });
      onRefresh?.();
    } catch (error) {
      console.error(error);
      alert("No se pudo registrar la salida");
    } 
  }

  const registrosFiltrados = useMemo(() => {
    // sin filtro → devolvemos todo
    if (mesSeleccionado === "Mes" && !anioSeleccionado) return data.registros;

    return data.registros.filter(r => {
      const d = new Date(Date.parse(r.fechaInicio));
      const coincideMes  = mesSeleccionado === "Mes" || d.getMonth() === meses.indexOf(mesSeleccionado);
      const coincideAnio = !anioSeleccionado       || d.getFullYear().toString() === anioSeleccionado;
      return coincideMes && coincideAnio;
    });
  }, [data.registros, mesSeleccionado, anioSeleccionado]);

  return (
    <Col className="p-3" id="jornadas" lg xl xxl={4} md={6} sm={12}>
      <Row className="bg-success"><h2>Registro Jornada</h2></Row>

      <Row className="p-1">
        <Col xs={8}>
          <Row>
              <Col>
                  <Button variant="primary" className="w-100" onClick={handleEntrada}>Entrar</Button>
              </Col>
              <Col>
                <Button variant="primary" className="w-100" onClick={handleSalida}>Salir</Button> 
              </Col>
          </Row>
          <Row className="p-1">
            <Col sm={6}>
              <Row>
                <DropdownButton as={ButtonGroup} title={mesSeleccionado} id="mes-dropdown"
                    onSelect={key => setMesSeleccionado(meses[key - 1])}>
                      {meses.map((mes, i) => (
                      <Dropdown.Item key={mes} eventKey={i + 1}>{mes}</Dropdown.Item>
                      ))}
                </DropdownButton>
              </Row>
            </Col>

            <Col sm={6}>
              <Form.Control 
                value={anioSeleccionado} 
                placeholder="Año" 
                className="mr-sm-2" 
                onChange={e => setAnioSeleccionado(e.target.value)}/>
            </Col>
          </Row>
        </Col>

        <Col className="d-flex flex-column justify-content-around align-items-center" xs={4}>
          <Row>Trabajando</Row>
          <Row>01 : 00 h</Row>
        </Col>
      </Row>

      <Row className="p-1">
        <Table striped bordered hover size="sm">
          <thead>
            <tr>
              <th>Entrada</th>
              <th>Salida</th>
              <th>Duración</th>
            </tr>
          </thead>
          <tbody>
              {registrosFiltrados.map((fila, idx) => (
              <tr key={idx}>
                <td>{formatearFecha(fila.fechaInicio)}</td>
                <td>{fila.fechaFin ? formatearFecha(fila.fechaFin) : "—"}</td>
                <td>{duracion(fila.fechaInicio, fila.fechaFin)}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Row>
    </Col>
  );
}

function duracion(inicioISO, finISO) {
  // Si fin no existe (turno abierto), usamos la hora actual
  const inicio = new Date(inicioISO);
  const fin    = finISO ? new Date(finISO) : new Date();

  const minutos = Math.floor((fin - inicio) / 60000);  // ms → min
  const hh = String(Math.floor(minutos / 60)).padStart(2, "0");
  const mm = String(minutos % 60).padStart(2, "0");

  return `${hh}:${mm} h`;
}

function formatearFecha(iso) {
  return new Date(Date.parse(iso)).toLocaleString("es-ES", {
    day:   "2-digit",
    month: "2-digit",
    year:  "numeric",
    hour:  "2-digit",
    minute:"2-digit",
    timeZone: "Europe/Madrid"
  });
}

export default RegistroJornada;
