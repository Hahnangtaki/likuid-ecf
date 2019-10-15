import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './fund-raiser-bank-my-suffix.reducer';

export interface IFundRaiserBankMySuffixDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FundRaiserBankMySuffixDeleteDialog extends React.Component<IFundRaiserBankMySuffixDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.fundRaiserBankEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { fundRaiserBankEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="likuidecfApp.fundRaiserBank.delete.question">Are you sure you want to delete this FundRaiserBank?</ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-fundRaiserBank" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ fundRaiserBank }: IRootState) => ({
  fundRaiserBankEntity: fundRaiserBank.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FundRaiserBankMySuffixDeleteDialog);
