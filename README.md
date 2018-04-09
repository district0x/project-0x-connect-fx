# project-0x-connect-fx

## Usage

Warning: WORK IN PROGRESS

```clojure
(re-frame/reg-event-fx
 :my-event
 (fn [{:keys [:db]}]
   (let [client (get-in db [:client])]
     {:0x-connect [{:http-client client
                    :fn :get-fees-async
                    :args [{:exchange-contract-address "0x12459c951127e0c374ff9105dda097662a027093"
                            :maker "0x9e56625509c2f60af937f23b7b532600390e8c8b"
                            :taker "0x0000000000000000000000000000000000000000"
                            :maker-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                            :taker-token-address "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"
                            :maker-token-amount "10000000000000000"
                            :taker-token-amount "20000000000000000"
                            :expiration-unix-timestamp-sec "42"
                            :salt "67006738228878699843088602623665307406148487219438534730168799356281242528500"}
                           {:page 1
                            :per-page 2}]
                    :on-success [::on-success]
                    :on-error [::on-error]}
                   {:http-client client
                      :fn :get-orderbook-async
                      :args [{:base-token-address "0xe41d2489571d322189246dafa5ebde1f4699f498"
                              :quote-token-address "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"}]
                      :on-success [::on-success]
                      :on-error [::on-error]}
                   {:http-client client
                    :fn :get-order-async
                    :args ["0x2c66066520f33adeeb2dffb23c68287261fce778bff7ed8e22ea614fa202fd96"]
                    :on-success [::on-success]
                    :on-error [::on-error]}
                   {:http-client client
                      :fn :get-orders-async
                      :on-success [::on-success]
                      :on-error [::on-error]}
                   {:http-client client
                    :fn :get-token-pairs-async
                    :args [{:token-a "0x89d24a6b4ccb1b6faa2625fe562bdd9a23260359"
                            :token-b "0x01b3ec4aae1b8729529beb4965f27d008788b0eb"}]
                    :on-success [::on-success]
                    :on-error [::on-error]}
                   #_{:ws-orderbook-channel ws-chan
                      :fn :subscribe
                      :args [{:base-token-address "0x123"}]
                      :on-close [::on-close]
                      :on-error [::on-error]
                      :on-snapshot [::on-snapshot]
                      :on-update [::on-update]}
                   #_{:ws-orderbook-channel ws-chan
                      :fn :close}]})))
```
