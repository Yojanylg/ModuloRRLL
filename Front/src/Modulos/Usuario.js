import 'bootstrap/dist/css/bootstrap.min.css';
import { Col, Row } from 'react-bootstrap';

function Usuario({ data = {} }) {

  return (
    <Col className='p-3'>
      <Row className='bg-success'>
        <Row><h2>Usuario</h2></Row>
      </Row>
      <Row className='text-start'>
        <p></p>
        <p><strong>Nombre: </strong>{data.nombre}</p>
        <p><strong>Apellidos: </strong> {data.apellido1} {data.apellido2}</p>
        <p><strong>Email: </strong> {data.email}</p>
        <p><strong>Direccion: </strong> {data.direccion}</p>
      </Row>
    </Col>
  );
}

export default Usuario;