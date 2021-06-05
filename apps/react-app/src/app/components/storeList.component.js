import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class StoreList extends React.Component {
  constructor(props) {
    super(props);

    this.getStoreList = this.getStoreList.bind(this);
    this.deleteStore = this.deleteStore.bind(this);

    this.state = {
      stores: [],
    };
  }

  getStoreList() {
    httpService
      .get('/stores')
      .then((response) => {
        console.log('getStoreList Response :');
        console.log(response.data);

        this.setState({
          stores: response.data,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteStore(id, e) {
    httpService
      .delete('/stores/' + id)
      .then((response) => {
        console.log('deleteStore Response :');
        console.log(response);
        this.getStoreList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getStoreList();
  }

  render() {
    console.log(this.state);
    const storeList = this.state.stores.map((store, index) => {
      return (
        <Container>
          <br />
          <Row key={index}>
            <Col>
              {store.storeID}
            </Col>
            <Col>
              {store.name}
            </Col>
            <Col>
              {store.address}
            </Col>
            <Col>
              {store.customers.length}
            </Col>
            <Col>
              {store.products.length}
            </Col>
            <Col>
              <Link
                to={{
                  pathname: '/update',
                  state: {
                    id: store.storeID,
                  },
                }}
              >
                <Button variant="dark">Update</Button>
              </Link>{' '}
              <Button
                variant="light"
                onClick={(e) => this.deleteStore(store.storeID, e)}
              >
                Delete
              </Button>
            </Col>
          </Row>
        </Container>
      );
    });

    return (
      <Container>
        <h3 style={{ textAlign: 'center' }}>Store List</h3>
        <Link to={'/create'}>
          <Button variant="outline-dark">Add new Store</Button>
        </Link>
        <Container>
          <Row>
            <Col>Store ID</Col>
            <Col>Name</Col>
            <Col>Address</Col>
            <Col>Customers</Col>
            <Col>Products</Col>
            <Col>Actions</Col>
          </Row>
          {storeList}
        </Container>
      </Container>
    );
  }
}
