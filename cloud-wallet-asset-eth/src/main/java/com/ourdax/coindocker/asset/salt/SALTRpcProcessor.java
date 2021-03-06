package com.ourdax.coindocker.asset.salt;

import com.ourdax.coindocker.asset.EthContractRpcProcessor;
import com.ourdax.coindocker.common.base.AssetException;
import com.ourdax.coindocker.common.base.RpcCallTemplate;
import com.ourdax.coindocker.common.enums.AssetCode;
import com.ourdax.coindocker.contract.ERC20Contract;
import com.ourdax.coindocker.enums.ERC20Enum;
import com.ourdax.coindocker.rpc.RpcTransRequest;
import com.ourdax.coindocker.rpc.RpcTransResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

/**
 * @author think on 13/1/2018
 */
@Component(value = "SALTRpcProcessor")
@Lazy
@Slf4j
public class SALTRpcProcessor extends EthContractRpcProcessor {

  @Autowired
  private Admin client;

  private ERC20Enum erc20Enum;

  @Override
  protected Admin getClient() {
    return client;
  }

  @PostConstruct
  public void init() {
    TransactionManager manager = new ClientTransactionManager(client, getDepositAddress());
    erc20Enum = ERC20Enum.fromAssetCode(getAssetCode());
    contract = ERC20Contract.load(erc20Enum.getBinary(),
        erc20Enum.getContractId(), client, manager, GAS_PRICE, GAS_LIMIT);
  }

  @Override
  public AssetCode getAssetCode() {
    return AssetCode.SALT;
  }

  @Override
  protected String getContractAddress() {
    return erc20Enum.getContractId();
  }

  @Override
  protected int getDecimals() {
    return erc20Enum.getDecimal();
  }
}
