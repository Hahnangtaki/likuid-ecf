import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './investor-institution-my-suffix.reducer';

export interface IInvestorInstitutionMySuffixDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorInstitutionMySuffixDeleteDialog extends React.Component<IInvestorInstitutionMySuffixDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.investorInstitutionEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { investorInstitutionEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="likuidecfApp.investorInstitution.delete.question">
          Are you sure you want to delete this InvestorInstitution?
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-investorInstitution" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ investorInstitution }: IRootState) => ({
  investorInstitutionEntity: investorInstitution.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorInstitutionMySuffixDeleteDialog);
